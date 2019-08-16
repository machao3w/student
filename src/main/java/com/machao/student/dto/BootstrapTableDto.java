package com.machao.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BootstrapTableDto {

    private Integer total;

    private Object data;
}
