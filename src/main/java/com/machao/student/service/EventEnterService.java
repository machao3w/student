package com.machao.student.service;

import com.machao.student.entity.EventEnter;

public interface EventEnterService {

     void register(EventEnter eventEnter);

     boolean checkNumer(String number);
}
