package com.machao.student.controller;

import com.machao.student.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @Author mc
 * @Data 2019/10/9 14:14
 */
@RestController
public class ExcelController {


    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "output",method = RequestMethod.POST)
    public void outputExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = excelService.excelSample();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + new String("测试标题".getBytes("GB2312"),"ISO-8859-1"));
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

}
