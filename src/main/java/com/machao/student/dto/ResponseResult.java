package com.machao.student.dto;

import lombok.Data;

@Data
public class ResponseResult {
    private Integer code;
    private String message;
    private Object data;

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
