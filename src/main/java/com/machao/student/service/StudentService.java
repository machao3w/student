package com.machao.student.service;

import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

public interface StudentService {

    ResponseResult selectByPrimaryKey(String number);

    String listAll(Integer offset, Integer limit, String number, String name, Integer grade, Integer classes, String projects, Integer minGrade, Integer maxGrade);
}
