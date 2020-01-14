package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.machao.student.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImpTest {

    @Autowired
    StudentServiceImp studentServiceImp;

    @Test
    public void selectByPrimaryKey() throws Exception{
        Student result = studentServiceImp.selectByPrimaryKey("20181011033");
        System.out.println(JSON.toJSONString(result));

    }

    @Test
    public void testList(){
        Student student = studentServiceImp.selectByPrimaryKey("20181011033");
        //Student student1 = (Student) student.getData();
        student.setNumber("8mqyxy4lkkd");
        //System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testJson(){
        String json = "{\"code\":200,\"info\":{\"token\":\"yunxin_dev_vdzxkrd286xetl5p\",\"accid\":\"yunxin_dev_vdzxkrd286xetl5p\",\"name\":\"aa\"}}";
        Map yunXinResultMap = JSON.parseObject(json);
        System.out.println(yunXinResultMap);
        JSONObject object = (JSONObject) yunXinResultMap.get("info");
        String accid = object.getString("accid");
        System.out.println(yunXinResultMap.get("info"));
        System.out.println(accid);
        System.out.println(LocalDateTime.now().toString());
        List<Integer> test = new ArrayList<>();
        List<Integer> test1 = test.stream().collect(Collectors.toList());
        System.out.println(test);
    }

    public void testBoolean(){
        Boolean b = null;
        Integer i = b !=null && b ? 1 : 0;
        System.out.println(b);
    }
}
