package com.machao.student.service.Imp;

import com.machao.student.dao.EventEnterMapper;
import com.machao.student.entity.EventEnter;
import com.machao.student.service.EvenEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventEnterServiceImp implements EvenEnterService {

    @Autowired
    private EventEnterMapper eventEnterMapper;

    @Override
    public void register(EventEnter eventEnter) {
        eventEnterMapper.insert(eventEnter);
    }
}
