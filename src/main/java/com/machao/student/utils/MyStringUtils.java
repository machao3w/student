package com.machao.student.utils;

import com.machao.student.enums.ParamError;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.aggregated.BeanMetaData;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public class MyStringUtils {

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
