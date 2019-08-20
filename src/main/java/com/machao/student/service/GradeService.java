package com.machao.student.service;

public interface GradeService {

    String listAllAndSort(Integer offset, Integer limit, Integer limitFront, Integer limitBehind, String improvement,Integer grade,Integer classes);
}
