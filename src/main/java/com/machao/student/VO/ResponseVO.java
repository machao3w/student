package com.machao.student.VO;

import com.machao.student.enums.ErrorCode;
import com.machao.student.utils.EnumUtil;
import lombok.Data;

@Data
public class ResponseVO<T> {

    private Integer code;
    private String message;
    private T data;


    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> result = new ResponseVO<>();
        result.setCode(0);
        result.setMessage("响应成功");
        result.setData(data);
        return result;
    }

    public static ResponseVO success(){
        return success(null);
    }

    public static ResponseVO fail() {
        ResponseVO result = new ResponseVO();
        result.setCode(500);
        result.setMessage("响应失败");
        return result;
    }

    public static ResponseVO error(Integer code){
        ResponseVO result = new ResponseVO();
        result.setCode(code);
        result.setMessage(getErrorCodeEnum(result.getCode()).getMessage());
        return result;
    }


    public static ResponseVO error(ErrorCode errorCodeEnum){
        ResponseVO result = new ResponseVO();
        result.setCode(errorCodeEnum.getCode());
        result.setMessage(errorCodeEnum.getMessage());
        return result;
    }


    private static ErrorCode getErrorCodeEnum(Integer code){
        if(code != null){
            return EnumUtil.getByCode(code, ErrorCode.class);
        }
        return EnumUtil.getByCode(404,ErrorCode.class);
    }

}

