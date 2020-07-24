package com.machao.student.configure;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: mc
 * date: 2020/7/20 15:07
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig  {

    @Bean
    public Docket createRestApi() {

        Set<String> producesSet = new HashSet<>();
        producesSet.add("application/json");
        producesSet.add("charset=utf-8");

//        ParameterBuilder parameterBuilder=new ParameterBuilder();
//        List<Parameter> parameters= Lists.newArrayList();
//        parameterBuilder.name(SecurityConsts.REQUEST_AUTH_HEADER).description("登录认证").modelRef(new ModelRef("String"))
//                .parameterType("header")
//                .required(false).build();
//        parameters.add(parameterBuilder.build());
//
//        parameterBuilder.name("AppKey").description("开发者平台分配的appkey").modelRef(new ModelRef("string")).parameterType("header").required(true);
//        parameters.add(parameterBuilder.build());
//        parameterBuilder.name("Nonce").description("随机数（最大长度128个字符）").modelRef(new ModelRef("string")).parameterType("header").required(true);
//        parameters.add(parameterBuilder.build());
//        parameterBuilder.name("CurTime").description("当前UTC时间戳").modelRef(new ModelRef("int")).parameterType("header").required(true);
//        parameters.add(parameterBuilder.build());
//        parameterBuilder.name("CheckSum").description("1.对访问接口的参数值进行排序,拼接成消息；AppKey/Nonce/CurTime/传的参数；2.使用HMACSHA1 (消息，appsecret)进行加密 3.每个checkSum的有效期为5分钟  ")
//                .modelRef(new ModelRef("string")).parameterType("header").required(true);
//        parameters.add(parameterBuilder.build());
////        String env = PropertyUtils.getProperty("active");
//        String env = myProperties.getActive();

        return new Docket(DocumentationType.OAS_30)
//                .enable(!("prod".equalsIgnoreCase(env)||"test".equalsIgnoreCase(env)))//是否开启swagger
                //.enable(!("prod".equalsIgnoreCase(env)))//是否开启swagger
                .apiInfo(apiInfo())
                .produces(producesSet)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//只扫描有@Api注解的类
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//只扫描有@ApiOperation的方法
//                .apis(RequestHandlerSelectors.basePackage("com.lancet.controller"))//扫描包
                .paths(PathSelectors.any())
                .build()
                ;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("backstage_all:" + " Restful APIs")
                .description("")
                .termsOfServiceUrl("lancet.com").version("1.0").build();
    }
}
