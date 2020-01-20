package com.machao.student.controller;

import com.machao.student.Exception.NormalException;
import com.machao.student.VO.ResponseVO;
import com.machao.student.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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



}
