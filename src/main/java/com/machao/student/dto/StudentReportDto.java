package com.machao.student.dto;

import com.machao.student.entity.GradeFinal;
import com.machao.student.entity.GradeMid;
import com.machao.student.entity.Student;
import lombok.Data;

@Data
public class StudentReportDto {

    private Student student;

    private GradeMid gradeMid;

    private GradeFinal gradeFinal;

}
