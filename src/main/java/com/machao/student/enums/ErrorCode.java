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
    AUTH_ERROR(401,"认证错误"),
    RECORD_NOT_FOUND(404,"未找到记录"),
    ACCESS_LIMIT(1000,"访问限制"),
    VERIFY_CODE_EMPTY(1001,"验证码不为空"),
    VERIFY_CODE_EXPIRE(1002,"验证码已过期"),
    VERIFY_CODE_ERROR(1003,"验证码错误"),
    ;
    private Integer code;
    private String message;
}
