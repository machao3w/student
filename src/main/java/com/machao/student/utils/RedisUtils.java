package com.machao.student.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 枷锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            System.out.println(key);
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        log.info("【Redis Service 】 ,key = {}", key);
        log.info("【Redis Service 】 ,value = {}", value);
        log.info("【Redis Service 】 ,currentValue = {}", currentValue);
        if(!StringUtils.isEmpty(currentValue)
                &&System.currentTimeMillis() < Long.parseLong(currentValue)){
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(currentValue)
                    &&oldValue.equals(currentValue)){
                return true;
            }
        }

        return false;
    }


    public void unlock(String key, String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue)
                    &&currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("redis解锁错误");
        }

    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, Constants.TIMES, TimeUnit.SECONDS);
    }

    public void set(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }
}
