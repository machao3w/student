package com.machao.student.service.Imp;

import com.machao.student.dao.GradeFinalMapper;
import com.machao.student.dao.GradeMidMapper;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.ResponseResult;
import com.machao.student.dto.StudentReportDto;
import com.machao.student.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentReportServiceImp implements StudentReportService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GradeFinalMapper gradeFinalMapper;

    @Autowired
    private GradeMidMapper gradeMidMapper;

    @Override
    public ResponseResult selectByPrimaryKey(String number) {

        return null;
    }

    @Override
    public List<StudentReportDto> selectAll() {
        return null;
    }
}
