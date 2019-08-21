package com.machao.student.Exception;

public class RegisterException extends RuntimeException {
    private Integer code;

    public RegisterException(Integer code, String message){
        super(message);
        this.code=code;
    }
}
