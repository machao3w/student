package com.machao.student.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * author: mc
 * date: 2020/1/15 9:33
 */
@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //User user =new User(s,new BCryptPasswordEncoder().encode("user"), AuthorityUtils.NO_AUTHORITIES);
        log.info("表单登陆username："+ s);
        return new SocialUser(s,new BCryptPasswordEncoder().encode("user"), AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        log.info("社交登陆用户id：" + s);
        return new SocialUser(s,new BCryptPasswordEncoder().encode("user"), AuthorityUtils.NO_AUTHORITIES);
    }
}
