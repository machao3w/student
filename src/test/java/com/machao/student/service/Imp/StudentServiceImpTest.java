package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dto.ResponseResult;
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
}
