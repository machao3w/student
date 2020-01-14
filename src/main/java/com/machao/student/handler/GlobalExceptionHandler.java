package com.machao.student.handler;

import com.machao.student.Exception.NormalException;
import com.machao.student.VO.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author: mc
 * date: 2020/1/14 9:48
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = NormalException.class)
    public ResponseVO myErrorHandler(NormalException e) {
        log.info("【报错信息】：" + e.getMessage());
        return ResponseVO.error(e.getCode());
    }
}
