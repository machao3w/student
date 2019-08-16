package com.machao.student.controller;

import com.machao.student.dto.ResponseResult;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/studentList")
    public String listAll(){
        return null;
    }

    @GetMapping("/studentList/{number}")
    public ResponseResult selectByPrimaryKey(@PathVariable("number") String number){
        return studentService.selectByPrimaryKey(number);
    }
}
