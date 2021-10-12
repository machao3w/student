package com.machao.student.configure;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * author: mc
 * date: 2021-09-26
 */
@Component
@EnableScheduling
public class MySchedule {


    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 1000)
    public void sendMessageToUser(){
//        simpMessagingTemplate.convertAndSend("/topic/shouts", "123456");

        simpMessagingTemplate.convertAndSendToUser("admin","/queue/shouts",System.currentTimeMillis()+"admin");
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessageToUser2(){
//        simpMessagingTemplate.convertAndSend("/topic/shouts", "123456");

        simpMessagingTemplate.convertAndSendToUser("machao","/queue/shouts",System.currentTimeMillis()+"machao");
    }
}
