package com.machao.student.controller;

import com.machao.student.dto.ResponseResult;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/studentList")
    public String listAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "number", required = false) String number,
                          @RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "grade", required = false) Integer grade,
                          @RequestParam(value = "classes", required = false) Integer classes,
                          @RequestParam(value="projects",required = false) String projects){

        return studentService.listAll(offset,limit,number,name,grade,classes,projects);
    }

    @GetMapping("/studentList/{number}")
    public ResponseResult selectByPrimaryKey(@PathVariable("number") String number){
        return studentService.selectByPrimaryKey(number);
    }



}
