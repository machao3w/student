package com.machao.student.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author: mc
 * date: 2020/1/13 13:50
 */

@Getter
@AllArgsConstructor
public enum ParamError {

    TEST(1,"用户名重复","the username has been in used"),
    ;

    private Integer code;

    private String message;

    private String messageEn;

}
