package com.machao.student.aop;

import com.machao.student.Exception.NormalException;
import com.machao.student.annotaion.RedisLock;
import com.machao.student.enums.ErrorCode;
import com.machao.student.utils.MD5Utils;
import com.machao.student.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * author: mc
 * date: 2020/1/13 9:44
 */

//@Aspect
//@Component
@Slf4j
public class RedisLockAspect {

    @Autowired
    RedisUtils redisUtils;

    @Pointcut("@annotation(com.machao.student.annotaion.RedisLock)")
    public void lockRedis(){}

    @Around("lockRedis()")
    public Object doLock(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object o : args){
            sb.append(o.toString());
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedisLock annotation = method.getAnnotation(RedisLock.class);

        sb.append(method.toString());
        String key = MD5Utils.md5Encode(sb.toString());

        long value = System.currentTimeMillis() + annotation.value();

        if (!redisUtils.lock(key,String.valueOf(value))){
            throw NormalException.getInstance(ErrorCode.ACCESS_LIMIT);
        }
        log.info("枷锁");

        Object response = joinPoint.proceed(args);

        redisUtils.unlock(key,String.valueOf(value));
        log.info("解锁");
        return response;
    }
}
