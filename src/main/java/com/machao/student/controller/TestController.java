package com.machao.student.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * author: mc
 * date: 2021-09-27
 */
@RestController
public class TestController {


    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/singleShout")
    public void receive(Principal principal){
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/shouts", "shout");
    }

}
