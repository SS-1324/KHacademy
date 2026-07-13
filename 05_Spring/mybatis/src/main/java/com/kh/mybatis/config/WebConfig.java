package com.kh.mybatis.config;

import com.kh.mybatis.common.AdminCheckInterceptor;
import com.kh.mybatis.common.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    /*
        AdminCheckInterceptor를 addInterceptors메서드를 통해서 SpringMVC에 등록할 수 있음
        addPathPatterns / excludePathPatterns로 어떤 URL만 적용할지 지정 가능
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor())
                .addPathPatterns("/member/list")
                //목록조회는 interceptor를 통과해야하고 login, inserForm은 체크없이 자유롭게 접근 가능
                .excludePathPatterns("/member/login", "/member/insertForm");
    }
}
