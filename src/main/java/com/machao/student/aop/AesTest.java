package com.machao.student.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.machao.student.utils.Hashidse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: mc
 * date: 2020/3/5 10:17
 */

@Aspect
@Component
public class AesTest {

    private static ArrayList<String> keys =new ArrayList<>();

    static{
        keys.add("doctorid");
        keys.add("sdoctor");
        keys.add("rdoctor");
        keys.add("expertid");
        keys.add("fromid");
        keys.add("toid");
        keys.add("clientid");
        keys.add("doctorId");
        keys.add("doctorIds");
    }


    @Pointcut("@annotation(com.machao.student.annotaion.EnableAes)")
    public void aex(){}

    @Around(value = "aex()")
    public Object doIdAex(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //解密参数
        Object[] params = joinPoint.getArgs();
        String[] paraNames = signature.getParameterNames();
        for (int i = 0; i < paraNames.length; i ++){
            if (params[i] == null || params[i] instanceof BindingResult || params[i] instanceof ServletRequest || params[i] instanceof ServletResponse){
                continue;
            }
            if (isBaseType(params[i].getClass())){
                if (keys.contains(paraNames[i])){
                    params[i] = Hashidse.decodeStr(params[i].toString());
                }
            }else if ( params[i] instanceof  ArrayList){
                if (keys.contains(paraNames[i])){
                    List list = (List)  params[i];
                    for (int j = 0; j < list.size(); j ++){
                        if (list.get(j) instanceof String){
                            list.set(j,Hashidse.decodeStr((String) list.get(j)));
                        }
                    }
                    params[i] = list;
                }
            }else {
                test(params[i],true);
            }
        }
        Object response = joinPoint.proceed(params);
        test(response,false);
        //加密参数
        return response;
    }


    /**
     *
     * @param obj
     * @param flag
     */
    private static void test(Object obj,boolean flag){
        if (obj == null || isBaseType(obj.getClass())){
            return;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                Method[] methods = obj.getClass().getMethods();
                Method method = null;
                for (Method item : methods) {
                    if (item.getName().equals("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))) {
                        method = item;
                        break;
                    }
                }
                if (method == null) {
                    continue;
                }
                if (keys.contains(fieldName)) {
                    if (field.getType().equals(String.class)) {
                        String value = (String) method.invoke(obj);
                        field.setAccessible(true);
                        field.set(obj, flag ? Hashidse.decodeStr(value) : Hashidse.encodeStr(value));
                    } else if (field.getType().getName().startsWith("java.util.List")) {
                        List list = (List) method.invoke(obj);
                        if (list != null && list.size() > 0 && list.get(0) instanceof String) {
                            List<String> list_new = (List<String>) list.stream().map(e -> flag ? Hashidse.decodeStr((String) e) : Hashidse.encodeStr((String) e)).collect(Collectors.toList());
                            field.setAccessible(true);
                            field.set(obj, list_new);
                        }
                    } else if (field.getType().isArray()) {
                        String[] array = (String[]) method.invoke(obj);
                        field.setAccessible(true);
                        field.set(obj, Arrays.stream(array).map(e -> flag ? Hashidse.decodeStr(e) : Hashidse.encodeStr(e)).collect(Collectors.toList()));
                    }
                } else if (field.getType().getName().startsWith("java.util.List")) {
                    List list = (List) method.invoke(obj);
                    if (list != null && list.size() > 0) {
                        for (Object o : list) {
                            test(o, flag);
                        }
                    }
                } else if (field.getType().getName().startsWith("java.util.Map")) {
                    Map map = (Map) method.invoke(obj);
                    if (map != null && map.size() > 0) {
                        for (Object key : map.keySet()) {
                            if (keys.contains(key.toString()) && map.get(key) instanceof String) {
                                field.setAccessible(true);
                                map.put(key, flag ? Hashidse.decodeStr((String) map.get(key)) : Hashidse.encodeStr((String) map.get(key)));
                            } else {
                                test(map.get(key), flag);
                            }
                        }
                    }
                } else if (!isBaseType(field.getType())) {
                    Object value = method.invoke(obj);
                    test(value, flag);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static <T> T modifyKey(JSON obj, boolean flag, Class<T> clazz){
        if (obj instanceof JSONObject){
            for (Map.Entry<String,Object> entry : ((JSONObject) obj).entrySet()){
                Object objNext = entry.getValue();
                if (keys.contains(entry.getKey())){
                    if (isBaseType(objNext.getClass())){
                        objNext = flag ? Hashidse.decodeStr(objNext.toString()) : Hashidse.encodeStr(objNext.toString());
                        entry.setValue(objNext);
                    }else if (objNext instanceof  JSONArray){
                        for (int i = 0; i <((JSONArray) objNext).size(); i ++){
                            Object newValue = flag ? Hashidse.decodeStr(((JSONArray) objNext).get(i).toString()) : Hashidse.encodeStr(((JSONArray) objNext).get(i).toString());
                            entry.setValue(newValue);
                        }
                    }
                }else if (objNext != null &&!isBaseType(objNext.getClass())){
                    modifyKey((JSON) JSONObject.toJSON(objNext),flag,objNext.getClass());
                }
            }
        }
        if (obj instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i <jsonArray.size(); i++){
                if (!isBaseType(jsonArray.get(i).getClass())) {
                    modifyKey((JSON) jsonArray.get(i), flag, jsonArray.get(i).getClass());
                }
            }
        }
        return JSONObject.toJavaObject(obj,clazz);
    }


    private static boolean isBaseType(Class className){
        //Class className = obj.getClass();
        return className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(String.class) ||
                className.equals(boolean.class);
    }
}
