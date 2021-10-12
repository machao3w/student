package com.machao.student.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * author: mc
 * date: 2020/3/5 10:52
 */
@Data
public class TestParam {

    @ApiModelProperty(notes = "医院id")
    @NotBlank
    private String doctorId;
    @ApiModelProperty(notes = "专家id")
    private String expertId;
    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    @JsonProperty(value = "timeTemp")
    private Date time;

    //private List<String> doctorIds;
}
