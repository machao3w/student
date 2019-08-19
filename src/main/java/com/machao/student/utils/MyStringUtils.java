package com.machao.student.utils;

import java.util.ArrayList;
import java.util.List;

public class MyStringUtils {

    /**
     * 字符串每个元素用%包围
     * "name" to "%n%%a%%m%%e%"
     * @param args
     */
    public static String StringAddPercent(String args){
        if(args.getBytes().length == args.length()){
            String[] a = args.replace(" ","").split("");
            List<String> list = new ArrayList<>();
            for(String temp : a){
                list.add("%"+temp+"%");
            }
            String args_new = String.join("",list);
            return args_new;
        }
        return null;
    }

    public static String StringToDBColumn(String args){
        String[] a = args.split(",");
        List<String> list = new ArrayList<>();
        for(String temp : a){
            if (temp.split("_")[1].equals("mid")){
                list.add("gm." + temp.split("_")[0]);
            } else if(temp.split("_")[1].equals("final")){
                list.add("gf." + temp.split("_")[0]);
            }
        }
        return String.join(",",list);
    }

}
