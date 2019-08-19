package com.machao.student.controller;

import com.machao.student.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

public class GradeController {

    @Autowired
    private GradeService gradeService;

    public String listAllSorted(@RequestParam(value = "grade",required = false) Integer grade,
                                @RequestParam(value = "classes",required = false) Integer classes){
        return gradeService.listAllAndSort(grade,classes);
    }

    public String listClasses(Integer grade, Integer classes) {
        return null;
    }

    public String listGradeImprove(){
        return null;
    }

}
