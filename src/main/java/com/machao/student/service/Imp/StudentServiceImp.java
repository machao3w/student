package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.annotaion.EnableAes;
import com.machao.student.dao.GradeFinalMapper;
import com.machao.student.dao.GradeMidMapper;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.BootstrapTableDto;
import com.machao.student.dto.ResponseResult;
import com.machao.student.entity.GradeMid;
import com.machao.student.entity.Student;
import com.machao.student.service.StudentService;
import com.machao.student.utils.GradeBetween;
import com.machao.student.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
    public String listAll(Integer offset, Integer limit,String number,String name,Integer grade, Integer classes, String projects, Integer minGrade, Integer maxGrade) {
        List<Student> studentList;
        List<Student> rows;
        //判断是否含有科目，名字是否包含中文
        if(!StringUtils.isEmpty(projects)) {
            String temp = MyStringUtils.StringToDBColumn(projects);
            if(StringUtils.isEmpty(name)||name.getBytes().length != name.length()){
                studentList = studentMapper.selectByPage01(number,name,grade,classes,temp);
            }else {
                studentList = studentMapper.selectByPage01ContainEnglish(number, MyStringUtils.StringAddPercent(name),grade,classes,temp);
            }
        } else {

            if (StringUtils.isEmpty(name) || name.getBytes().length != name.length()) {
                studentList = studentMapper.selectByPage(number, name, grade, classes);
            } else {
                studentList = studentMapper.selectByPageContainEnglish(number, MyStringUtils.StringAddPercent(name), grade, classes);
            }
        }
        //判断是否包含区间
        if(maxGrade!=null || minGrade != null){
            List<Student> studentList01 =new ArrayList<>();
            if(minGrade==null){
                minGrade=0;
            }
            for(Student student:studentList){
                if(GradeBetween.checkBetween(student,projects,minGrade,maxGrade)){
                    studentList01.add(student);
                }
            }
            studentList = studentList01;
        }
        //返回bootst-table所规定的json格式
        if(offset + 10 < studentList.size()){
            rows = studentList.subList(offset,offset+10);
        }else {
            rows = studentList.subList(offset,studentList.size());
        }
        Integer total = studentList.size();
        // Integer total = studentMapper.selectCount(null);
        BootstrapTableDto bootstrapTableDto = new BootstrapTableDto(total, rows);
        return JSON.toJSONString(bootstrapTableDto);
    }

    /**
    public String listAll01(Integer offset, Integer limit,String number,String name,Integer grade, Integer classes, String projects){
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",number);
        criteria.andLike("name_english",name);
        criteria.
        return null;
    }
     **/

    @Override
    @Transactional
    public void testTran() {
        Student student = new Student();
        studentMapper.insert(student);
        Student student1 = studentMapper.selectByPrimaryKey("20183116906");
        studentMapper.delete(student1);
    }

    @Override
    @EnableAes
    public ResponseResult testList(String number,Student student2) {
        Student student =  studentMapper.selectByPrimaryKey(number);
        Student student1 = studentMapper.selectByPrimaryKey(number);
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        if(student == null){
            return new ResponseResult(0,"数据为空");
        }
        return new ResponseResult(1,"查询成功",list);
    }
}
