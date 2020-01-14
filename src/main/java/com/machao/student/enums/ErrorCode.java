package com.machao.student.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author: mc
 * date: 2020/1/14 9:49
 */
@Getter
@AllArgsConstructor
public enum ErrorCode implements CodeEnum{
    RECORD_NOT_FOUND(404,"未找到记录"),
    ACCESS_LIMIT(1000,"访问限制"),
    ;
    private Integer code;
    private String message;
}
