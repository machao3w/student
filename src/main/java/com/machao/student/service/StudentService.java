package com.machao.student.service;

import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public interface StudentService {

    ResponseResult selectByPrimaryKey(String number);

    ResponseResult selectByName(String name);

    ResponseResult selectByClassesOrGrade(Map<String, Integer> map);
}
