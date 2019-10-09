package com.machao.student.controller;

import com.machao.student.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author mc
 * @Data 2019/10/9 14:14
 */
@RestController
public class ExcelController {


    @Autowired
    private ExcelService excelService;

    @GetMapping("/output")
    public void outputExcel(HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = excelService.excelSample();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=TEST.xls");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

}
