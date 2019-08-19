package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dao.GradeFinalMapper;
import com.machao.student.dao.GradeMidMapper;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.BootstrapTableDto;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.GradeMid;
import com.machao.student.entity.Student;
import com.machao.student.service.StudentService;
import com.machao.student.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GradeMidMapper gradeMidMapper;

    @Autowired
    private GradeFinalMapper gradeFinalMapper;

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
        List<Student> rows_new;
        if(!StringUtils.isEmpty(projects)) {
            String temp = MyStringUtils.StringToDBColumn(projects);
            if(StringUtils.isEmpty(name)||name.getBytes().length != name.length()){
                rows = studentMapper.selectByPage01(number,name,grade,classes,temp);
                List<Student> temp01 = studentMapper.selectByTest("20181011462");
                System.out.println(temp01);
            }else {
                rows = studentMapper.selectByPage01ContainEnglish(number, MyStringUtils.StringAddPercent(name),grade,classes,temp);
            }
        } else {

            if (StringUtils.isEmpty(name) || name.getBytes().length != name.length()) {
                rows = studentMapper.selectByPage(number, name, grade, classes);
            } else {
                rows = studentMapper.selectByPageContainEnglish(number, MyStringUtils.StringAddPercent(name), grade, classes);
            }
        }

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

//    public String listAll01(Integer offset, Integer limit,String number,String name,Integer grade, Integer classes, String projects){
//        Example example = new Example(Student.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("number",number);
//        criteria.andLike("name_english",name);
//        criteria.
//        return null;
//    }
}
