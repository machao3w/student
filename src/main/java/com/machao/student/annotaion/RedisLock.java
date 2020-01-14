package com.machao.student.annotaion;

import java.lang.annotation.*;

/**
 * author: mc
 * date: 2020/1/13 9:44
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    int value() default 100;
}
