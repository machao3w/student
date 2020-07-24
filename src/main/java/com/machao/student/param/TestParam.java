package com.machao.student.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * author: mc
 * date: 2020/3/5 10:52
 */
@Data
public class TestParam {

    @ApiModelProperty(notes = "医院id")
    private String doctorId;
    @ApiModelProperty(notes = "专家id")
    private String expertId;

    //private List<String> doctorIds;
}
