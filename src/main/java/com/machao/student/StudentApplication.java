package com.machao.student;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.machao.student.dao")
@RestController
//@EnableWebMvc
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

}
