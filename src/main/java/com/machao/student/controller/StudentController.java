package com.machao.student.controller;

import com.machao.student.dto.ResponseResult;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/studentList")
    public String listAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit){

        return studentService.selectByPage(offset,limit);
    }

    @GetMapping("/studentList/{number}")
    public ResponseResult selectByPrimaryKey(@PathVariable("number") String number){
        return studentService.selectByPrimaryKey(number);
    }
}
