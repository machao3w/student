package com.machao.student.service.Imp;

import com.machao.student.entity.EventEnter;
import com.machao.student.service.EventEnterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * author: mc
 * date: 2020/1/14 11:23
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventEnterServiceImpTest {

    @Autowired
    private EventEnterService eventEnterService;

    @Test
    public void register() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);
        Integer sum = lists.stream().reduce(0,(a,b) -> a + b);
        Integer sum1 = lists.parallelStream().reduce(0,(a,b) -> a + b);



        System.out.println(sum);
        System.out.println(sum1);
    }

    @Test
    public void checkNumer() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        Integer product = lists.parallelStream().reduce(1, (a, b) -> a *  (b * 2),
                (a, b) -> a * b);
        Integer product1 = lists.parallelStream().reduce(1,(a, b) -> {
            System.out.println("a=" + a + ",b=" + b);
            return a * b;
        },(a, b) -> a * b);
        System.out.println(product1);
        System.out.println("============");
        int result = lists.stream().reduce(1,(a, b) -> {
            System.out.println("a=" + a + ",b=" + b);
            return a * b;
        } );
        System.out.println(result);
        System.out.println("product:" + product);
        System.out.println("product1:" + product1);
    }

    @Test
    public void register0() {
        List<EventEnter> eventEnters = new ArrayList<>();
        EventEnter eventEnter = new EventEnter(null,"20181011066","test",1);
        EventEnter eventEnter1 = new EventEnter(null,"20181011066","test",1);
        EventEnter eventEnter2 = new EventEnter(null,"20181011066","test",1);
        eventEnters.add(eventEnter);
        eventEnters.add(eventEnter1);
        eventEnters.add(eventEnter2);
        //eventEnterService.register0(eventEnter);
        eventEnters.parallelStream().forEach(eventEnter3 -> eventEnterService.register0(eventEnter3));

    }
}
