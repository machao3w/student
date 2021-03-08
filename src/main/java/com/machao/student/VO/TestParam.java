package com.machao.student.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TestParam {

    private Boolean isLong;

    @JsonFormat
        private String test;
}
