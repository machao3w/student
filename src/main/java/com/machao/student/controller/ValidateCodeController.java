package com.machao.student.controller;

import com.alibaba.fastjson.JSON;
import com.machao.student.VO.ResponseVO;
import com.machao.student.service.ValidateImageService;
import com.machao.student.utils.Constants;
import com.machao.student.utils.RedisUtils;
import com.machao.student.validate.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * author: mc
 * date: 2020/1/16 16:04
 */

@RestController
public class ValidateCodeController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ValidateImageService validateImageService;

    @GetMapping("/code/image")
    public ResponseVO createCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String key = session.getId();
        ImageCode imageCode = validateImageService.createImageCode(request);
        //imageCode.getImage()
        redisUtils.set(Constants.CODE_PREFIX + key , JSON.toJSONString(imageCode),Constants.CODE_EXPIRE);
        return ResponseVO.success(imageCode.getImageBase64());

    }



//    private ImageCode createImageCode(HttpServletRequest request) {
//        int width = 67;
//        int height = 23;
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//        Graphics g = image.getGraphics();
//
//        Random random = new Random();
//
//
//        g.setColor(getRandColor(200, 250));
//        g.fillRect(0, 0, width, height);
//        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
//            g.setColor(getRandColor(160, 200));
//            //绘制干扰线
//        for (int i = 0; i < 155; i++) {
//            int x = random.nextInt(width);
//            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            g.drawLine(x, y, x + xl, y + yl);
//        }
//
//        String sRand = "";
//        for (int i = 0; i < 4; i++) {
//            String rand = String.valueOf(random.nextInt(10));
//            sRand += rand;
//            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
//            g.drawString(rand, 13 * i + 6, 16);
//        }
//
//        g.dispose();
//        return new ImageCode(image,sRand,60);
//    }
//
//    private Color getRandColor(int fc, int bc) {
//        Random random = new Random();
//        if (fc > 255) {
//            fc = 255;
//        }
//        if (bc > 255) {
//            bc = 255;
//        }
//        int r = fc + random.nextInt(bc - fc);
//        int g = fc + random.nextInt(bc - fc);
//        int b = fc + random.nextInt(bc - fc);
//        return new Color(r, g, b);
//    }
}
