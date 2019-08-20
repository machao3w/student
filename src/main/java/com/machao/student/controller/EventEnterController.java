package com.machao.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/event")
public class EventEnterController {


    @PostMapping("/enterform")
    public String enter(){
        return null;
    }
}
