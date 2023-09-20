package com.machao.student.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.machao.student.Exception.NormalException;
import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.ESAPI;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Order(value = 0)
//@WebFilter(urlPatterns = {"/*"})
public class ParamFilter implements Filter {

    private String  words;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);

        //打印参数日志
        log.info(logGetParams(requestWrapper));


        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
        if (parameterMap != null){
            for (String key : parameterMap.keySet()){
                if (parameterMap.get(key) != null){
                    String jsonString = JSON.toJSONString(parameterMap.get(key), SerializerFeature.WriteMapNullValue);
                    if (validParams(jsonString)){
                        throw new NormalException("不合法参数",501);
                    }
                }
            }
        }

        String paramsBody = getRequestPayload(requestWrapper);
        if (!StringUtils.isEmpty(paramsBody)){
            JSONObject jsonObject = JSONObject.parseObject(paramsBody, Feature.OrderedField);
            for (String key : jsonObject.keySet()){
                if (jsonObject.get(key) != null){
                    String jsonString = JSON.toJSONString(jsonObject.get(key), SerializerFeature.WriteMapNullValue);
                    if (validParams(jsonString)){
                        throw new NormalException("不合法参数",501);
                    }
                }
            }
        }

        filterChain.doFilter(requestWrapper,servletResponse);

    }


    //请求打印日志
    private String logGetParams(RequestWrapper requestWrapper){
        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
        StringBuilder sb = new StringBuilder("url参数=========\n");
        parameterMap.forEach((k,v) -> sb.append(k).append(": ").append(Arrays.toString(v)).append("\n"));
        sb.append("body参数=========\n");
        sb.append(getRequestPayload(requestWrapper)).append("\n");
        return sb.toString();

    }



    private Boolean validParams(String str){
        String pattern = ".*\\<.*\\>.*";
        return Pattern.matches(pattern,str);
    }


    private String getRequestPayload(RequestWrapper req) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
