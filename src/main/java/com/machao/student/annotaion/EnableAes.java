package com.machao.student.annotaion;

import java.lang.annotation.*;

/**
 * author: mc
 * data: 2019/11/28 10:44
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited
public @interface EnableAes {
}
