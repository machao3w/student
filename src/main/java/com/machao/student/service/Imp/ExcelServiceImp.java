package com.machao.student.service.Imp;

import com.machao.student.dto.ProductDTO;
import com.machao.student.dto.ProductDetailDTO;
import com.machao.student.dto.ShopDTO;
import com.machao.student.service.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mc
 * @Data 2019/10/8 11:24
 */

@Service
public class ExcelServiceImp implements ExcelService {

    @Override
    public HSSFWorkbook excelSample() {

        ProductDetailDTO productDetailDTO = new ProductDetailDTO("yellow",1,1,1,1,3);
        ProductDetailDTO productDetailDTO1 = new ProductDetailDTO("red",2,2,2,2,6);
        ProductDetailDTO productDetailDTO2 = new ProductDetailDTO("blue",3,3,3,3,9);
        List<ProductDetailDTO> detailDTOList = new ArrayList<>();
        detailDTOList.add(productDetailDTO);
        detailDTOList.add(productDetailDTO1);
        detailDTOList.add(productDetailDTO2);

        ProductDTO productDTO = new ProductDTO("衬衣",detailDTOList);
        ProductDTO productDTO1 = new ProductDTO("裤子",detailDTOList);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(productDTO);
        productDTOList.add(productDTO1);

        ShopDTO shopDTO = new ShopDTO("2017年5月1日",productDTOList);
        ShopDTO shopDTO1 = new ShopDTO("2017年6月1日",productDTOList);
        List<ShopDTO> shopDTOList = new ArrayList<>();
        shopDTOList.add(shopDTO);
        shopDTOList.add(shopDTO1);


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("test");

        //设置样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //style.setAlignment();

        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("微软雅黑");

        int row = 0;

        //创建表头 第一行表头
        String[] header = {"日期","商品名称","颜色","型号","","","平均数","总数"};
        String[] headerMerge = {"0,1,0,0","0,1,1,1","0,1,2,2","0,0,3,5","0,1,6,6","0,1,7,7"};

        HSSFRow row1 = sheet.createRow(row++);
        for (int i =0; i<header.length; i++){
            HSSFCell cell = row1.createCell(i);  //创建单元格
            cell.setCellValue(header[i]);  //单元格赋值
            cell.setCellStyle(style);  //cell 设置样式
            sheet.autoSizeColumn(i,true); //列宽度根据元素长度自动拓展
            sheet.autoSizeColumn(i);
        }
        //合并表头
        for (int i=0; i<headerMerge.length; i++){
            String[] temp = headerMerge[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow,overrow,startcol,overcol));
        }

        //创建第二行表头
        String[] header1 = {"","","","XS","S","L","",""};

        HSSFRow row2 = sheet.createRow(row++);
        for (int i =0; i<header1.length; i++){
            HSSFCell cell = row2.createCell(i);
            cell.setCellValue(header1[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        //逐行插入数据
        HSSFRow rowData = sheet.createRow(row++);
        for (ShopDTO shop : shopDTOList){
            int col = 0;
            HSSFCell cell = rowData.createCell(col);
            cell.setCellValue(shop.getAddtime());
            cell.setCellStyle(style);
            sheet.autoSizeColumn(col);

            //合并单元格
            List<Integer> tempList = shop.getProducts().stream().map(e ->e.getDetails().size()).collect(Collectors.toList());
            int temp = tempList.stream().mapToInt(Integer::intValue).sum();  //计算第一单元格需要占用的列数
            sheet.addMergedRegion(new CellRangeAddress(row-1,row+temp-1-1,col,col)); //由于row++ 是先赋值在运算 所以要-1

            for (ProductDTO product : shop.getProducts()){
                col = 1;
                cell = rowData.createCell(col);
                cell.setCellValue(product.getProductName());
                cell.setCellStyle(style);
                sheet.autoSizeColumn(col);

                //合并单元格
                sheet.addMergedRegion(new CellRangeAddress(row-1,row+product.getDetails().size()-1-1,col,col));

                for (ProductDetailDTO detail : product.getDetails()){
                    col = 2;
                    //利用反射的方法将对象中所有属性逐个添加 适用于String和包装类
                    Field[] fields = detail.getClass().getDeclaredFields();
                    for (int i = 0; i < fields.length; i++){
                        cell = rowData.createCell(col+i);
                        //sheet.autoSizeColumn(col+i);
                        String name = fields[i].getName();
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                        Method m = null;
                        try {
                            m = detail.getClass().getMethod("get" +name);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        String type = fields[i].getGenericType().toString();
                        try {
                            if (type.equals("class java.lang.String")){
                                String value = (String) m.invoke(detail);
                                cell.setCellValue(value);
                            }else if (type.equals("class java.lang.Integer")){
                                Integer value = (Integer) m.invoke(detail);
                                cell.setCellValue(value);
                            }
                        }catch (IllegalAccessException e){
                            e.printStackTrace();
                        }catch (InvocationTargetException e){
                            e.printStackTrace();
                        }
                        cell.setCellStyle(style);
                    }

                    rowData = sheet.createRow(row++);
                }
                //rowData = sheet.createRow(row++);
            }
        }
        return wb;
    }
}
