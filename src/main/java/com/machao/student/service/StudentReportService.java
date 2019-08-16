package com.machao.student.service;


import com.machao.student.dto.ResponseResult;
import com.machao.student.dto.StudentReportDto;

import java.util.List;

public interface StudentReportService  {

    ResponseResult selectByPrimaryKey(String number);

    List<StudentReportDto> selectAll();

}
