package com.machao.student.service.Imp;

import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResponseResult selectByPrimaryKey(String number) {
        Student student =  studentMapper.selectByPrimaryKey(number);
        if(student == null){
            return new ResponseResult(0,"数据为空");
        }
        return new ResponseResult(1,"查询成功", student);
    }

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
}
