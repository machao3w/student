package com.machao.student.validate;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * author: mc
 * date: 2020/1/16 16:12
 */

@Data
public class ImageCode {

    private String imageBase64;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(String imageBase64, String code, long expireInt) {
        this.imageBase64 = imageBase64;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
    }
}
