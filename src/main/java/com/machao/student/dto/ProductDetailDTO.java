package com.machao.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author mc
 * @Data 2019/10/8 13:52
 */

@Data
@AllArgsConstructor
public class ProductDetailDTO {

    private String colorName;

    private Integer XS;

    private Integer S;

    private Integer L;

    private Integer average;

    private Integer sum;
}
