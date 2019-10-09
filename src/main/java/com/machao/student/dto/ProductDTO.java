package com.machao.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author mc
 * @Data 2019/10/8 13:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productName;

    private List<ProductDetailDTO> details;


}
