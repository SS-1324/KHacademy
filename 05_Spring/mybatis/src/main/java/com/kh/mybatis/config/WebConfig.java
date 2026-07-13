package com.kh.mybatis.config;

import com.kh.mybatis.common.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    @Configuration : spring의 IoC컨테이너가 관리하는 설정을 하는 클레스다.

    1. Been등록 : @Configuration 클래스의 메소드의 @Been어노테이션이 붙이면 Been객체 생성 메서드로 활용 가능.
    2. CoponentScan의 대상 : @Configuration 클래스는 @Component의 기능을 포함한다.
 */


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter(){
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*"); //모든 요청을 필터로 처리
        registrationBean.setOrder(1); // filter가 여러개일 때 실행 순서
        return registrationBean;
    }
}
