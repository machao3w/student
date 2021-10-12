package com.machao.student.controller;

import com.machao.student.Exception.NormalException;
import com.machao.student.VO.ResponseVO;
import com.machao.student.enums.ErrorCode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * author: mc
 * date: 2020/1/15 11:17
 */
@Controller
@Slf4j
public class LoginController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("将要跳转的请求是：" +targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html") ){
                redirectStrategy.sendRedirect(request,response,"/login/loginPage");
            }
        }
        redirectStrategy.sendRedirect(request,response,"/login/loginPage");

    }

    @RequestMapping("/login/loginPage")
    public String loginPage(){
        return "login";
    }


    @RequestMapping("/login/qrpage")
    public String qrpage(){
        return "register";
    }

    @RequestMapping("/login/png")
    public void getImage(HttpServletResponse response){
        try {
            BufferedImage imageQr = getRemoteImageAndSave("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGt8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLUh0SUF4UlZjdkQxMDAwMGcwN0oAAgQdu4tgAwQAAAAA");
            BufferedImage imageLoc = ImageIO.read(new File("D:\\workspace\\student\\src\\main\\resources\\static\\img\\dingwei12.png"));
            BufferedImage imageOri = ImageIO.read(new File("C:\\Users\\MSI-PC\\Desktop\\svjsz-5kix5-001.png"));
            Graphics2D graphics = imageOri.createGraphics();

            //创建位置图片
            Font font = new Font("宋体",Font.BOLD,100);
            String storeName = "百信缘药店荷花池分店啊啊啊啊啊";
            graphics.drawImage(imageQr,3450,1000,900,900,null);

            graphics.setFont(font);
            graphics.setColor(Color.WHITE);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawString(storeName,4800-100*storeName.length(),  750);
            graphics.drawImage(imageLoc,4800-100*storeName.length()-imageLoc.getWidth()-100,600,150,150,null);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(storeName, "UTF-8") + ".png");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("image/png");
            ImageIO.write(imageOri, "png", response.getOutputStream());
        } catch (IOException e){
            log.error("Exception:",e);
        }

    }

    public static BufferedImage getRemoteImageAndSave(String url){
        try {
            if (!StringUtils.hasLength(url)){
                return null;
            }
            if (url.startsWith("http")){
                BufferedImage bufferedImage = ImageIO.read(Unirest.get(url).asBinary().getBody());
                return bufferedImage;
            }
        } catch (UnirestException | IOException e) {
            log.error("Exception:",e);
        }
        return null;
    }



}
