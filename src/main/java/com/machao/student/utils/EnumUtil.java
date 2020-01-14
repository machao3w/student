package com.machao.student.utils;

import com.machao.student.enums.CodeEnum;

public class EnumUtil {

    /**
     * 枚举类通过id获得message
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T  getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return  each;
            }
        }
        return null;
    }

}
