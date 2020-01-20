package com.machao.student.filter;

import com.machao.student.service.ValidateImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author: mc
 * date: 2020/1/17 17:23
 */
@Slf4j
@Component
public class ValidCodeFilter extends OncePerRequestFilter {


    private ValidateImageService validateImageService;

    @Autowired
    public ValidCodeFilter(ValidateImageService validateImageService) {
        this.validateImageService = validateImageService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase("/loginPage",httpServletRequest.getRequestURI())){
            String sessionId = httpServletRequest.getSession().getId();
            String verifyCode = (String) httpServletRequest.getParameterValues("imageCode")[0];
            log.info("sessionId=="  + sessionId );
            log.info("verifyCode==" + verifyCode);
            validateImageService.checkCode(sessionId,verifyCode);
        }


        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
