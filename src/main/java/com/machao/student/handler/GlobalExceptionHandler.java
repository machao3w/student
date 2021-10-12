package com.machao.student.handler;

import com.machao.student.Exception.NormalException;
import com.machao.student.VO.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO myErrorHandler0(MethodArgumentNotValidException e){
        List<String> msgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msgList.add(error.getDefaultMessage());
        }
        return ResponseVO.success(String.join(",",msgList));
    }

}
