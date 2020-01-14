package com.machao.student.service;

import com.machao.student.entity.Student;

import java.util.List;

public interface StudentService {

    Student selectByPrimaryKey(String number);

    String listAll(Integer offset, Integer limit, String number, String name, Integer grade, Integer classes, String projects, Integer minGrade, Integer maxGrade);

    void testTran();

    List<Student> testList(String number,Student student2);
}
