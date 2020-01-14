package com.machao.student.annotaion;

import com.machao.student.aop.MyConstrainValidator;
import com.machao.student.enums.ParamError;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: mc
 * date: 2020/1/13 16:11
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstrainValidator.class)
public @interface MyValid {

    ParamError enumName();

    Class<?> validAnn();

    String message() default "{javax.validation.constraints.NotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
