package com.machao.student.dto;

import com.machao.student.enums.ParamError;
import com.machao.student.utils.MyStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BootstrapTableDto {

    private Integer total;

    private Object data;
}
