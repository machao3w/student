package com.machao.student.service;

import com.machao.student.validate.ImageCode;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * author: mc
 * date: 2020/1/17 10:28
 */

public interface ValidateImageService {

    ImageCode createImageCode(HttpServletRequest request);

    void checkCode(String sessionId, String verifyCode);


}
