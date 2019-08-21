package com.machao.student.utils;

import com.machao.student.entity.GradeFinal;
import com.machao.student.entity.GradeMid;
import com.machao.student.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GradeSum {

    /**
     * 计算学生的期中成绩
     * @param student
     * @return
     */
    public static Integer SumMid(Student student) {
        GradeMid gradeMid = student.getGradeMid();
        List<Integer> list = new ArrayList<>();
        Field[] field = gradeMid.getClass().getDeclaredFields();
        for(int i=0;i<field.length;i++){
            String name = field[i].getName();
            if(name!="numberM"){
                name = name.substring(0,1).toUpperCase()+name.substring(1);
                String type = field[i].getGenericType().toString();
                if(type.equals("class java.lang.String")){
                    Method m = null;
                    try {
                        m = gradeMid.getClass().getMethod("get"+name);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    String value = null;
                    try {
                        value = (String) m.invoke(gradeMid);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if(value.getBytes().length==value.length()){
                        list.add(Integer.valueOf(value));
                    }
                }
            }
        }
        int sum = 0;
        for (int i:list){
            sum = sum + i;
        }
        return sum;
    }

    /**
     * 计算学生的期末成绩
     * @param student
     * @return
     */
    public static Integer SumFinal(Student student) {
        GradeFinal gradeFinal = student.getGradeFinal();
        List<Integer> list = new ArrayList<>();
        Field[] field = gradeFinal.getClass().getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            if (name != "numberF") {
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = field[i].getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    Method m = null;
                    try {
                        m = gradeFinal.getClass().getMethod("get" + name);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    String value = null;
                    try {
                        value = (String) m.invoke(gradeFinal);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (value.getBytes().length == value.length()) {
                        list.add(Integer.valueOf(value));
                    }
                }
            }
        }
        int sum = 0;
        for (int i : list) {
            sum = sum + i;
        }
        return sum;
    }
}
