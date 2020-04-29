package com.machao.student.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @Author mc
 * @Data 2019/10/8 11:21
 */
public interface ExcelService {

    HSSFWorkbook excelSample();

    Runnable test();
}
