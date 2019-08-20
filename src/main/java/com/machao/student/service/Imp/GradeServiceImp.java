package com.machao.student.service.Imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.dao.StudentMapper;
import com.machao.student.dto.BootstrapTableDto;
import com.machao.student.VO.StudentReportVO;
import com.machao.student.entity.Student;
import com.machao.student.service.GradeService;
import com.machao.student.utils.GradeSum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GradeServiceImp implements GradeService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public String listAllAndSort(Integer offset, Integer limit, Integer limitFront, Integer limitBehind, String improvement, Integer grade, Integer classes) {
        List<Student> studentList = studentMapper.selectAllAndGrade(grade,classes);
        List<StudentReportVO> studentReportVOList = new ArrayList<>();
        for(Student student : studentList){
            StudentReportVO studentReportVO = new StudentReportVO();
            BeanUtils.copyProperties(student, studentReportVO);
            Integer gradeMid = GradeSum.SumMid(student);
            studentReportVO.setGradeMid(gradeMid);
            Integer gradeFinal = GradeSum.SumFinal(student);
            studentReportVO.setGradeFinal(gradeFinal);
            studentReportVOList.add(studentReportVO);
        }
        Collections.sort(studentReportVOList, (o1, o2) -> {
            if(o1.getGradeFinal()==null){
                return 1;
            }
            if(o2.getGradeFinal()==null){
                return -1;
            }
            return o2.getGradeFinal().compareTo(o1.getGradeFinal());
        });

        if(!StringUtils.isEmpty(limitFront)){
            studentReportVOList = studentReportVOList.subList(0,limitFront);
        }
        if(!StringUtils.isEmpty(limitBehind)){
            Collections.reverse(studentReportVOList);
            studentReportVOList = studentReportVOList.subList(0,limitBehind);
        }
        List<StudentReportVO> rows;
        Integer total;
        if(!StringUtils.isEmpty(improvement)){
            List<StudentReportVO> studentReportVOList_new = new ArrayList<>();
            for (StudentReportVO studentReportVO : studentReportVOList){
                if(Integer.valueOf(studentReportVO.getGradeFinal()) > Integer.valueOf(studentReportVO.getGradeMid())){
                    studentReportVOList_new.add(studentReportVO);
                }
            }
            if(offset + 10 < studentReportVOList_new.size()){
                rows = studentReportVOList_new.subList(offset,offset+10);
            }else {
                rows = studentReportVOList_new.subList(offset, studentReportVOList_new.size());
            }
            total = studentReportVOList_new.size();
        }else {
            if(offset + 10 < studentReportVOList.size()){
                rows = studentReportVOList.subList(offset,offset+10);
            }else {
                rows = studentReportVOList.subList(offset, studentReportVOList.size());
            }
            total = studentReportVOList.size();
        }
        BootstrapTableDto bootstrapTableDto = new BootstrapTableDto(total, rows);
        return JSON.toJSONString(bootstrapTableDto);
    }
}
