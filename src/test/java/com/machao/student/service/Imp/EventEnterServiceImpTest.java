package com.machao.student.service.Imp;

import com.machao.student.entity.Event;
import com.machao.student.entity.EventEnter;
import com.machao.student.entity.Student;
import com.machao.student.service.EventEnterService;
import com.machao.student.utils.MyStringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

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
        Event event1 = new Event(1,"拔河",null);
        Event event2 = new Event(2,"拔河",null);
        Event event3 = new Event(3,"拔河",null);
        Event event4 = new Event(4,"拔河",null);
        Event event5 = new Event(5,"拔河",null);
        Event event6 = new Event(6,"拔河",null);
        Map<Integer,Event> eventMap = new HashMap<>();
        eventMap.put(1,event1);
        eventMap.put(2,event2);
        eventMap.put(3,event3);
        eventMap.put(4,event4);
        eventMap.put(5,event5);
        eventMap.put(6,event6);
        System.out.println(eventMap);
        List<Integer> ids= new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);
        System.out.println(ids);
        List<Event> events = ids.parallelStream().collect(ArrayList::new,(a, b)-> a.add(eventMap.get(b)), ArrayList::addAll);
        System.out.println(events);



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
//        eventEnters.parallelStream().forEach(eventEnter3 -> eventEnterService.register0(eventEnter3));
        String idStr = "1,2,3,4,5,6";
        List<Integer> list = Arrays.stream(idStr.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> list1 = new ArrayList<>();
        List<Integer> all = (List<Integer>) CollectionUtils.intersection(list,list1);
        System.out.println(all);
        System.out.println(list);

        Integer[] ids = {1,3};
        System.out.println(ids);
        ids = new Integer[]{3, 4};
        System.out.println(ids);

        char ch = 'a';
        System.out.println(ch);

        String result = MyStringUtils.unicode2String("\\u5566");
        String result1 = MyStringUtils.unicode2String("啦");
        System.out.println(result1);

    }

    @Test
    public void TestString(){
        System.out.printf("HI,%s%n","zhangsan");
        System.out.printf("HI,%s,%s:%s%n","张三","李四","王五");
        System.out.printf("1+1=2，%b%n",1==1);
        System.out.printf("折扣价%d%%%n",85);
    }
}
