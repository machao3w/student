package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.BootstrapTableDto;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import com.machao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public String listAll(Integer offset, Integer limit,String number,String name,Integer grade, Integer classes) {
        if(!StringUtils.isEmpty(number)||!StringUtils.isEmpty(name)||!StringUtils.isEmpty(grade)||!StringUtils.isEmpty(classes)){
            offset = 0;
            limit = 10;
        }
        List<Student> rows = studentMapper.selectByPage(offset,limit,number,name,grade,classes);
        Integer total = studentMapper.selectCount(null);
        BootstrapTableDto bootstrapTableDto = new BootstrapTableDto(total, rows);
        return JSON.toJSONString(bootstrapTableDto);
    }
}
