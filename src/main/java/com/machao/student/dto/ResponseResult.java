package com.machao.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    private Integer code;
    private String message;
    private Object data;

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
