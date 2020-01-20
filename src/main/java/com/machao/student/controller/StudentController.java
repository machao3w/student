package com.machao.student.controller;

import com.machao.student.VO.ResponseVO;
import com.machao.student.annotaion.EnableAes;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
//@EnableAes
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/studentList")
    //@EnableAes
    public String listAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "number", required = false) String number,
                          @RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "grade", required = false) Integer grade,
                          @RequestParam(value = "classes", required = false) Integer classes,
                          @RequestParam(value="projects",required = false) String projects,
                          @RequestParam(value = "minGrade",required = false) Integer minGrade,
                          @RequestParam(value = "maxGrade",required = false)Integer maxGrade){

        return studentService.listAll(offset,limit,number,name,grade,classes,projects,minGrade,maxGrade);
    }

    @GetMapping("/studentList/{number}")
   // @EnableAes
    @ResponseBody
    public ResponseVO selectByPrimaryKey(@EnableAes @PathVariable("number") String number){
        //System.out.println("test");
        return ResponseVO.success(studentService.selectByPrimaryKey(number));
    }

    @GetMapping("/testTran")
    @EnableAes
    public String test(){
        studentService.testTran();
        return "test";
    }




}
