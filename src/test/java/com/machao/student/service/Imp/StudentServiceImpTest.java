package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.machao.student.entity.Student;
import com.machao.student.utils.Hashidse;
import com.machao.student.utils.MyStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Test
    public void testBoolean() throws IOException {
//        Boolean b = null;
//        Integer i = b !=null && b ? 1 : 0;
//        System.out.println(b);

        String url = "/test/test123";
        String new_url = url.substring(0,url.indexOf("/",url.indexOf("/")+1));
        System.out.println(new_url);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDateTime = LocalDateTime.now().format(formatter);
        System.out.printf(localDateTime);
        Integer a = Integer.MAX_VALUE + 10;
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE + 1);
        InputStream in = MyStringUtils.class.getClassLoader().getResourceAsStream("application.yml");
        Properties properties = new Properties();
        properties.load(in);
        String result = Hashidse.decodeStr(Hashidse.encodeStr("123"));
        String result1 = Hashidse.decodeStr(Hashidse.encodeInt(123));
        System.out.println(Hashidse.encodeStr("123"));
        Calendar c = Calendar.getInstance();
        String date = (c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE);
        System.out.println(date);
        System.out.println(LocalDate.now().toString());

    }
}
