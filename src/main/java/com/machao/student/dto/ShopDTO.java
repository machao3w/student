package com.machao.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author mc
 * @Data 2019/10/8 13:35
 */

@Data
@AllArgsConstructor
public class ShopDTO {

    private String addtime;

    private List<ProductDTO> products;
}
