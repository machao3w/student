package com.machao.student.aop;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machao.student.annotaion.EnableAes;
import com.machao.student.utils.Hashidse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * author: mc
 * data: 2019/11/28 10:37
 */

@Aspect
@Component
public class AesAspect {

    //@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    @Pointcut("@annotation(com.machao.student.annotaion.EnableAes)")
    public void AexRestController(){}

    @Around("AexRestController()")
    public Object doAexRestController(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println();
        ObjectMapper objectMapper = new ObjectMapper();
        //解密参数
        String[] paramNames = signature.getParameterNames();
        System.out.println(paramNames[1]);
        Object[] params = joinPoint.getArgs();
        System.out.println(params[0]);
        //Map<String,Object> paramsMap = new HashMap<>();
        for (int i = 0;i < paramNames.length;i++){
            if (paramNames[i].equals("number")){
                params[i] = Hashidse.decodeStr((String) params[i]);
            }else {
                Object obj = params[i];
                Map<String,Object> mappedObject = objectMapper.convertValue(obj,Map.class);
                processMap(mappedObject,1);
                params[i] = objectMapper.convertValue(mappedObject,obj.getClass());
            }
        }
        //加密response
        Object response = joinPoint.proceed(params);
        Map<String,Object> mappedObject = objectMapper.convertValue(response,Map.class);
        processMap(mappedObject,0);
        System.out.println(mappedObject);
        System.out.println("test");
        objectMapper.convertValue(mappedObject,response.getClass());
        return objectMapper.convertValue(mappedObject,response.getClass());

    }



    public static ArrayList<String> keys = new ArrayList<String>();
    static {
        keys.add("doctorid");
        keys.add("sdoctor");
        keys.add("rdoctor");
        keys.add("expertid");
        keys.add("fromid");
        keys.add("toid");
        keys.add("clientid");
        keys.add("number");
    }



    public Map<String,Object> processMap(Map<String,Object> map,Integer temp){

        for(String key : map.keySet()){
            Object object = map.get(key);
            if (object instanceof Map) {
                processMap((Map<String,Object>)object,temp);
            }else if(object instanceof ArrayList){
                processMap((ArrayList)object,temp);
            }else if (keys.contains(key)) {
                String string = object.toString();
                try {
                    //Integer.parseInt(string);
                    if (temp == 0){
                        map.put(key, Hashidse.encodeStr(object.toString()));
                    }else {
                        map.put(key, Hashidse.decodeStr(object.toString()));
                    }
                } catch (Exception e) {

                }
            }
        }
        return map;
    }

    public ArrayList processMap(ArrayList list,Integer temp){
        for(int i = 0; i < list.size(); i++){
            Object object = list.get(i);
            if (object instanceof Map) {
                processMap((Map<String,Object>)object,temp);
            }else if(object instanceof ArrayList){
                processMap((ArrayList)object,temp);
            }
        }
        return list;
    }

}
