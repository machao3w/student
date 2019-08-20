package com.machao.student.controller;

import com.machao.student.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/report")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ResponseBody
    @GetMapping("/reportList01")
    public String listAllSorted(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                @RequestParam(value = "limitFront",required = false) Integer limitFront,
                                @RequestParam(value = "limitBehind", required = false) Integer limitBehind,
                                @RequestParam(value = "improvement", required = false) String improvement,
                                @RequestParam(value = "grade", required = false) Integer grade,
                                @RequestParam(value = "classes", required = false) Integer classes) {
        return gradeService.listAllAndSort(offset,limit,limitFront,limitBehind,improvement,grade,classes);
    }

    @RequestMapping("/reportList")
    public String list(){
        return "gradeList";
    }

}
