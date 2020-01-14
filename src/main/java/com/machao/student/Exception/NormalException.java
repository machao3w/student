package com.machao.student.Exception;

import com.machao.student.enums.ErrorCode;
import lombok.Getter;

/**
 * author: mc
 * date: 2020/1/14 9:46
 */

@Getter
public class NormalException extends RuntimeException {

    private Integer code;

    public NormalException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public static NormalException getInstance(ErrorCode errorCode){
        return new NormalException(errorCode.getMessage(),errorCode.getCode());
    }


}
