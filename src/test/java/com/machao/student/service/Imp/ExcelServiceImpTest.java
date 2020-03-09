package com.machao.student.service.Imp;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * author: mc
 * date: 2020/1/21 11:07
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelServiceImpTest {


    @Test
    public void excelSample() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        int i  = style.getIndex();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle style3 = wb.getCellStyleAt(21);
        HSSFCellStyle style4 =wb.getCellStyleAt(0);

        System.out.println(wb);

    }
}
