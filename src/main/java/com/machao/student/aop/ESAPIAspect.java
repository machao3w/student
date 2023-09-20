package com.machao.student.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.owasp.esapi.ESAPI;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ESAPIAspect {


    @Pointcut("execution(* com.machao.student.controller..*.*(..))")
    public void modifyStringParam(){}

    @Around(value = "modifyStringParam() ")
    public Object doESAPI(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] params = joinPoint.getArgs();
        if (params != null){
            for (Object param : params){
                if (param instanceof String){
                    String strParam = (String) param;
                    int index = ArrayUtils.indexOf(params,param);
                    if (!strParam.equals("")){
                        params[index] = ESAPI.encoder().encodeForHTML((String)param);
                    }
                }
            }
        }


        Object response = joinPoint.proceed(params);
        return response;

    }
}
