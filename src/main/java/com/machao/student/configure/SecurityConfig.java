package com.machao.student.configure;

import com.machao.student.filter.ValidCodeFilter;
import com.machao.student.service.Imp.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import javax.validation.constraints.Null;

/**
 * author: mc
 * date: 2020/1/15 10:08
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailService userDetailsService;

    private ValidCodeFilter validCodeFilter;
    private DataSource dataSource;
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    public SecurityConfig(MyUserDetailService userDetailsService, ValidCodeFilter validCodeFilter,DataSource dataSource,SpringSocialConfigurer springSocialConfigurer) {
        this.userDetailsService = userDetailsService;
        this.validCodeFilter = validCodeFilter;
        this.dataSource = dataSource;
        this.springSocialConfigurer = springSocialConfigurer;

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl  tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginPage")
//                .failureHandler(failureHandler)
//                .successHandler(successHandler)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/login/**","/code/image","/css/**","/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        //super.configure(http);
    }
}
