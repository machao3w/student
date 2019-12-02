package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImpTest {

    @Autowired
    StudentServiceImp studentServiceImp;

    @Test
    public void selectByPrimaryKey() throws Exception{
        ResponseResult result = studentServiceImp.selectByPrimaryKey("20181011033");
        System.out.println(JSON.toJSONString(result));

    }

    @Test
    public void testList(){
        ResponseResult student = studentServiceImp.selectByPrimaryKey("20181011033");
        Student student1 = (Student) student.getData();
        student1.setNumber("8mqyxy4lkkd");
        ResponseResult result = studentServiceImp.testList("8mqyxy4lkkd", student1);
        //System.out.println(JSON.toJSONString(result));
    }
}
