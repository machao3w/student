package com.machao.student.service;

import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;

public interface StudentService {
    Student selectByPrimaryKey(String number);


}
