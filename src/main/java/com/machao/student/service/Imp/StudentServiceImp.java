package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.BootstrapTableDto;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.Student;
import com.machao.student.service.StudentService;
import com.machao.student.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        return new ResponseResult(1,"查询成功",student);
    }

    @Override
    public String listAll(Integer offset, Integer limit,String number,String name,Integer grade, Integer classes, String projects) {
        /**
        if(!StringUtils.isEmpty(number)||!StringUtils.isEmpty(name)||!StringUtils.isEmpty(grade)||!StringUtils.isEmpty(classes)){
            offset = 0;
            limit = 10;
        }
        **/
        List<Student> rows;
        if(name.getBytes().length != name.length()){
             rows = studentMapper.selectByPage(number,name,grade,classes);
        }else {
             rows = studentMapper.selectByPageContainEnglish(number, MyStringUtils.StringAddPercent(name),grade,classes);
        }
        List<Student> rows_new;
        if(offset + 10 < rows.size()){
            rows_new = rows.subList(offset,offset+10);
        }else {
            rows_new = rows.subList(offset,rows.size());
        }
        Integer total = rows.size();
        // Integer total = studentMapper.selectCount(null);
        BootstrapTableDto bootstrapTableDto = new BootstrapTableDto(total, rows_new);
        return JSON.toJSONString(bootstrapTableDto);
    }
}
