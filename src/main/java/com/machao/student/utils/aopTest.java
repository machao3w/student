//package com.lancet.config.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.lancet.dao.system.AdminDao;
//import com.lancet.dao.system.OperationLogDao;
//import com.lancet.enums.CRUDMethod;
//import com.lancet.model.system.Admin;
//import com.lancet.model.system.OperationLog;
//import com.lancet.service.system.SystemService;
//import com.lancet.util.ContextHolderUtils;
//import com.lancet.util.PropertyUtils;
//import com.lancet.util.ReflectUtils;
//import com.lancet.util.StringUtil;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.repository.core.CrudMethods;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.*;
//
///**
// * author: mc
// * date: 2020/4/28 14:09
// */
//@Aspect
//@Component
//public class CUDSqlLogAspect {
//
//    @Autowired
//    private OperationLogDao operationLogDao;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private SystemService systemService;
//
//    @Autowired
//    private AdminDao adminDao;
//
//    @Pointcut("execution(* com.lancet.dao..*.update*(..))")
//    @Order(1)
//    public void update(){}
//
//    @Pointcut("execution(* com.lancet.dao..*.insert*(..))")
//    @Order(2)
//    public void insert(){}
//
//    @Pointcut("execution(* com.lancet.dao..*.delete*(..))")
//    @Order(3)
//    public void delete(){}
//
//    @Around(value = "update() || insert() || delete() ")
//    public Object addCUDLog(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        Object[] params = joinPoint.getArgs();
//        Class clazz = (Class) AopUtils.getTargetClass(joinPoint.getTarget()).getGenericInterfaces()[0];
//
//        if (clazz.equals(OperationLogDao.class) || !clazz.getName().startsWith("com.lancet.dao")){
//            Object response = joinPoint.proceed(params);
//            return response;
//        }
//        String sheetName = this.changeToSheetName(clazz.getSimpleName());
////        Object o = applicationContext.getBean(clazz);
//        String methodName = joinPoint.getSignature().getName();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Object preValue = null;
//        Object postValue = null;
//
//        String cud = "";
//
//        if (methodName.startsWith("update")){
//            cud = CRUDMethod.update.name();
//            if (methodName.startsWith("updateByExample")){
//                Object pojoParam = params[1];
//                if (pojoParam != null){
//                    preValue = this.getPreValue(clazz,pojoParam,true);
//                    Object preValueTemp = this.getPreValue(clazz,pojoParam,true);
//                    ReflectUtils.copyPropertiesIgnoreNull(params[0],preValueTemp);
//                    postValue = preValueTemp;
//                }
//            }else {
//                Object pojoParam = params[0];
//                if (pojoParam != null){
//                    Object primaryKey = this.getPrimaryKey(pojoParam);
//                    preValue = this.getPreValue(clazz,primaryKey,false);
//                    postValue = pojoParam;
//
//                }
//            }
//
//        }else if (methodName.startsWith("delete")){
//            cud = CRUDMethod.delete.name();
//            Object pojoParam = params[0];
//            switch (methodName) {
//                case "deleteByPrimaryKey":
//                    Object primaryKey = this.getPrimaryKey(pojoParam);
//                    preValue = this.getPreValue(clazz,primaryKey,false);
//                    break;
//                case "delete":
//                    preValue = pojoParam;
//                    break;
//                case "deleteByExample":
//                    preValue = this.getPreValue(clazz,pojoParam,true);
//                    break;
//                case "deleteByPrimaryKeyBatch":
//                    preValue = this.getPreValue(clazz, (Integer[]) pojoParam);
//                    break;
//            }
//
//        }
////        else if (methodName.startsWith("insert")){
////            cud = "create";
////            postValue =  params[0];
////        }
//
//        Object response = joinPoint.proceed(params);
//        if (methodName.startsWith("insert")){
//            cud = CRUDMethod.create.name();
//            if (methodName.equals("insertList")){
//                if (params[0] instanceof List){
//                    List<Object> param = (List<Object>) params[0];
//                    List<Object> pkList = new ArrayList<>();
//                    for (Object o : param){
//                        Object primaryKey = this.getPrimaryKey(o);
//                        pkList.add(primaryKey);
//                    }
//                    postValue = pkList;
//                }
//
//            }else {
//                Object primaryKey = this.getPrimaryKey(params[0]);
//                postValue = primaryKey ;
//            }
//
//        }
//        if (response.equals(1)){
//            Admin admin;
//            String env = PropertyUtils.getProperty("active");
//            if (env.equalsIgnoreCase("hd")  || env.equalsIgnoreCase("dev") || env.equalsIgnoreCase("local")){
//                admin = adminDao.selectByPrimaryKey(1);
//            }else {
//                admin  = systemService.queryAdminByToken();
//            }
//            OperationLog operationLog = this.createOperationLog(admin,cud,sheetName,JSON.toJSONString(preValue),JSON.toJSONString(postValue));
//            operationLogDao.insert(operationLog);
//        }
//
//        return response;
//    }
//
//
//
//    private String changeToSheetName(String daoName){
//        String newDaoName = daoName.replace("Dao","");
//        StringBuilder sb = new StringBuilder("core_");
//        for (char ch : newDaoName.toCharArray()){
//            if (Character.isUpperCase(ch)){
//                sb.append('_').append(Character.toLowerCase(ch));
//            }else {
//                sb.append(ch);
//            }
//        }
//        return sb.toString();
//    }
//
//
//    private OperationLog createOperationLog(Admin admin,String cud,String target,String preValue,String postValue){
//
//        String ip = ContextHolderUtils.getIp();
//        OperationLog operationLog = new OperationLog();
//        operationLog.setId(StringUtil.createOperationPK());
//        operationLog.setIp(ip);
//        operationLog.setNewValue(postValue);
//        operationLog.setOldValue(preValue);
//        operationLog.setTarget(target);
//        operationLog.setUser(admin.getUsername());
//        operationLog.setOperation(cud);
//        operationLog.setOperationTime(new Date());
//        return operationLog;
//    }
//
//
//    private Object getPreValue(Class clazz, Integer[] keyArray){
//        Object o = applicationContext.getBean(clazz);
//        Method method;
//        List<Object> res = new ArrayList<>();
//        try {
//            for (Integer key : keyArray){
//                method = o.getClass().getMethod("selectByPrimaryKey");
//                Object value = method.invoke(o,key);
//                res.add(value);
//            }
//        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
//            e.printStackTrace();
//        }
//        return res;
//    }
//
//
//    private Object getPreValue(Class clazz,Object primaryKey,boolean isExample){
//        Object o = applicationContext.getBean(clazz);
//        Method method = null;
//        Object value = null;
//        try {
//            for (Method m : o.getClass().getMethods()){
//                if (isExample){
//                    if (m.getName().contains("selectByExample")){
//                        method = m;
//                        break;
//                    }
//
//                }else {
//                    if (m.getName().contains("selectByPrimaryKey")){
//                        method = m;
//                        break;
//                    }
//
//                }
//            }
//            if (method == null){
//                return null;
//            }
//            value = method.invoke(o,primaryKey);
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//
//
//    private Object getPrimaryKey(Object pojo){
//        Annotation idClass = pojo.getClass().getAnnotation(IdClass.class);
//        Object primaryKey =  null;
//        if (idClass == null){
//            Field[] fields = pojo.getClass().getDeclaredFields();
//            for (Field field : fields){
//                String fieldName = field.getName();
//                if (field.isAnnotationPresent(Id.class)){
//                    try {
//                        Method method = pojo.getClass().getMethod("get" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1));
//                        primaryKey = method.invoke(pojo);
//                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }else {
//            Field[] fields = pojo.getClass().getDeclaredFields();
//            Map<String,Object> multiPK = new HashMap<>();
//            for (Field field : fields){
//                String fieldName = field.getName();
//                if (field.isAnnotationPresent(Id.class)){
//                    try {
//                        Method method = pojo.getClass().getMethod("get" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1));
//                        primaryKey = method.invoke(pojo);
//                        multiPK.put(fieldName,primaryKey);
//
//                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            primaryKey = multiPK;
//        }
//        return primaryKey;
//    }
//}
