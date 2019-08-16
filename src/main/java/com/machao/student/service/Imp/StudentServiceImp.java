package com.machao.student.service.Imp;

import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.ResponseResult;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResponseResult selectByPrimaryKey(String number) {
        return null;
    }

    @Override
    public ResponseResult selectByName(String name) {
        return null;
    }

    @Override
    public ResponseResult selectByClassesOrGrade(Map<String, Integer> map) {
        return null;
    }
}
