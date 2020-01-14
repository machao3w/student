package com.machao.student.controller;

import com.machao.student.VO.ResponseVO;
import com.machao.student.entity.EventEnter;
import com.machao.student.service.EventEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public ResponseVO enter(EventEnter eventEnter){
        eventEnterService.register(eventEnter);
        return ResponseVO.success();
    }

    @ResponseBody
    @PostMapping(value = {"/checknumber/{number1}","/checknumber"})
    public ResponseVO getRecord(@PathVariable(value = "number1",required = false) String number1, @RequestParam("number") String number, BindingResult result){
        if (result.hasErrors()){
            throw new RuntimeException();
        }
        boolean checkNumber = eventEnterService.checkNumer(number);
        if(checkNumber){
            return ResponseVO.success();
        }else {
            return ResponseVO.fail();
        }
    }
}
