package com.machao.student.utils;

import com.machao.student.entity.GradeFinal;
import com.machao.student.entity.GradeMid;
import com.machao.student.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GradeBetween {
    public static  boolean checkBetween(Student student, String project,Integer min, Integer max){
        GradeMid gradeMid = student.getGradeMid();
        GradeFinal gradeFinal = student.getGradeFinal();
        if(gradeMid==null){
            return checkBetweenUnit(gradeFinal,project,min,max);
        }
        if (gradeFinal==null){
            return checkBetweenUnit(gradeMid,project,min,max);
        }

        if(gradeMid!=null&&gradeFinal!=null){
            return checkBetweenUnit(gradeMid,project,min,max)&&checkBetweenUnit(gradeFinal,project,min,max);
        }
        return false;
    }

    /**
     * 判断学生成绩的值是否在区间内
     * @param obj   对象
     * @param props 属性已逗号分隔的字符串
     * @param min   属性下限
     * @param max 属性上限
     * @return
     */
    public static  boolean checkBetweenUnit(Object obj, String props, Integer min, Integer max){
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i =0;i< fields.length; i++){
            String name = fields[i].getName();
            for(String arg : props.split(",")){
                if (name.equals(arg)){
                    name = name.substring(0,1).toUpperCase()+name.substring(1);
                    String type = fields[i].getGenericType().toString();
                    if(type.equals("class java.lang.String")){
                        Method m = null;
                        try {
                            m = obj.getClass().getMethod("get" + name);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        String value = null;
                        try {
                            value = (String) m.invoke(obj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        if(value.getBytes().length==value.length()){
                            if(Integer.valueOf(value) < min || Integer.valueOf(value) > max){
                                //list.add(student);
                                return false;
                            }
                        }else {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }
}
