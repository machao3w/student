package com.machao.student.utils;

import com.machao.student.enums.ParamError;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfMargins;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.aggregated.BeanMetaData;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyStringUtils {


    public static void main(String[] args) throws IOException {


        //BufferedImage imageQr = ImageIO.read(new File("D:\\workspace\\student\\src\\main\\resources\\static\\img\\qr.png"));
        BufferedImage imageQr = getRemoteImageAndSave("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGt8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLUh0SUF4UlZjdkQxMDAwMGcwN0oAAgQdu4tgAwQAAAAA");
        BufferedImage imageLoc = ImageIO.read(new File("D:\\workspace\\student\\src\\main\\resources\\static\\img\\dingwei12.png"));
        BufferedImage imageOri = ImageIO.read(new File("C:\\Users\\MSI-PC\\Desktop\\svjsz-5kix5-001.png"));
        Graphics2D graphics = imageOri.createGraphics();

        //创建位置图片
        Font font = new Font("宋体",Font.BOLD,100);
        String storeName = "百信缘药店荷花池分店啊啊啊啊啊";
        graphics.drawImage(imageQr,3450,1000,900,900,null);



        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawString(storeName,4800-100*storeName.length(),  750);
        graphics.drawImage(imageLoc,4800-100*storeName.length()-imageLoc.getWidth()-100,600,150,150,null);
        ImageIO.write(imageOri, "png", new FileOutputStream("C:\\Users\\MSI-PC\\Desktop\\test123.png"));

    }


    public static BufferedImage getRemoteImageAndSave(String url){
        try {
            if (StringUtils.isEmpty(url)){
                return null;
            }
            if (url.startsWith("http")){
                BufferedImage bufferedImage = ImageIO.read(Unirest.get(url).asBinary().getBody());
                return bufferedImage;
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串每个元素用%包围
     * "name" to "%n%%a%%m%%e%"
     * @param args
     */
    public static String StringAddPercent(String args){
        if(args.getBytes().length == args.length()){
            String[] a = args.replace(" ","").split("");
            List<String> list = new ArrayList<>();
            for(String temp : a){
                list.add("%"+temp+"%");
            }
            String args_new = String.join("",list);
            return args_new;
        }
        return null;
    }

    public static String StringToDBColumn(String args){
        String[] a = args.split(",");
        List<String> list = new ArrayList<>();
        for(String temp : a){
            if(temp.matches("(.*)Mid")){
                list.add(temp.replaceAll("Mid","_mid"));
            }else {
                list.add(temp.replaceAll("Final","_final"));
            }


        }
        return String.join(",",list);
    }

    public static String getMessage(ParamError error){
//        if(getHeaderLanguage().equals("zh")){
//            ParamError paramError = ParamError.TEST;
//            paramError.getMessageEn();
            //Validator
            //BeanMetaDataManager beanMetaDataManager = new BeanMetaDataManager();
            //BeanMetaData root = beanMetaDataManager.getBeanMetaData(String.class);
            //if (root.hasConstraints()){
                //root.getMetaConstraints().add()
            //}

//            return  error.getMessage();
//        }else {
//            return error.getMessageEn();
//        }
        return null;
    }


    public static String getHeaderLanguage(){
        HttpServletRequest request = getRequest();
        String language = "zh";
        if (!StringUtils.isEmpty(request) && !StringUtils.isEmpty(request.getHeader("language"))) {
            language = request.getHeader("language");
        }
        return language;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public static String unicode2String(String pStr) {
        char aChar;
        int len = pStr.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = pStr.charAt(x++);
            if (aChar == '\\') {
                aChar = pStr.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = pStr.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

}
