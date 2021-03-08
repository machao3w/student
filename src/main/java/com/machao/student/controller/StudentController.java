package com.machao.student.controller;

import com.machao.student.VO.ResponseVO;
import com.machao.student.annotaion.EnableAes;
import com.machao.student.entity.Doctor;
import com.machao.student.enums.ParamError;
import com.machao.student.param.TestParam;
import com.machao.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "test")
//@EnableAes
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/studentList")
    //@EnableAes
    @ApiOperation(value = "学生列表")
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

    @PostMapping("/testTran")
    //@EnableAes
    @ApiOperation(value = "body测试")
    public Doctor test(@RequestBody TestParam error){
       // System.out.println(request);
        System.out.println("test");
//        studentService.testTran();
        Doctor doctor = new Doctor();
        doctor.setDoctorId("123");
        doctor.setDoctorName("machao");
        return doctor;
    }




}
