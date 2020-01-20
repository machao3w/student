package com.machao.student.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mc
 * @Data 2019/11/11 13:42
 */
public class HttpUnirestUtil {

    public static String sendPost(String url, Map<String, Object> parameters){
        try {
            Map<String,String> headers = new HashMap<>();
            headers.put("client","pc");
            headers.put("device","linux");
            headers.put("version","3.0.1");
            headers.put("accept", "application/json");
            HttpResponse<String> postResponse = Unirest.post(url)
                    .headers(headers)
                    .fields(parameters)
                    .asString();
            return postResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String sendPost(String url, Map<String, Object> parameters, Map<String,String> headers){
        try {
            HttpResponse<String> postResponse = Unirest.post(url)
                    .headers(headers)
                    .fields(parameters)
                    .asString();
            return postResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String get(String url) throws UnirestException {
        HttpResponse<String> response = Unirest.get(url).asString();
        return response.getBody();
    }

    public static String get(String url, Map<String,String> headers,Map<String,Object> params) {
        try {
            HttpResponse<String>  response = Unirest.get(url).queryString(params).headers(headers).asString();
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }
}
