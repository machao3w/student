package com.machao.student.aop;

import com.machao.student.annotaion.MyValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * author: mc
 * date: 2020/1/13 16:11
 */

public class MyConstrainValidator implements ConstraintValidator<MyValid,Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //constraintValidatorContext.buildConstraintViolationWithTemplate();
        return false;
    }

    @Override
    public void initialize(MyValid constraintAnnotation) {

    }
}
