package com.machao.student.controller;

import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.EventEnter;
import com.machao.student.service.EventEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event")
public class EventEnterController {

    @Autowired
    private EventEnterService eventEnterService;

    @RequestMapping("/enterform")
    public String showform(){
        return "registerForm";
    }

    @PostMapping("/enterform01")
    @ResponseBody
    public ResponseResult enter(EventEnter eventEnter){
        eventEnterService.register(eventEnter);
        return ResponseResult.success();
    }

    @ResponseBody
    @PostMapping("/checknumber")
    public ResponseResult getRecord(@RequestParam("number") String number){
        boolean checkNumber = eventEnterService.checkNumer(number);
        if(checkNumber){
            return ResponseResult.success();
        }else {
            return ResponseResult.fail();
        }
    }
}
