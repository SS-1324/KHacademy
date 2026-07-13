package com.kh.mybatis.common;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    처리시간 로그를 남기는 필터
    자바 프로젝트에서는 @WebFilter(경로)를 통해 직접 필터를 등록하고 사용함.
    필터는 클래스로 구성하고 실제 등록은 config class를 만들어서 명시적으로 Been에 등록해서 사용.
    (필터나 인터셉터의 등록을 한곳에 모아두면 URL 패턴, 적용순서를 관리하기 쉽다)

    Filter는 DispatcherServlet전에 서블릿컨테이서 레벨에서 동작.
    그래서 Filter는 해당 요청이 어떤 Controller로 연결되는지 알 수 없다.
    오직 HttpServletRequest/HttpServletResponse를 통해서만 판단해야한다.
 */
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String url = servletRequest.getRequestURI();
        String method = servletRequest.getMethod();

        long start = System.currentTimeMillis();
        System.out.println("요청 시작 : " +  method + " " + url);

        //다음 필터 또는 DispatcherServlet으로 요청을 전달(정상동작 진행)
        chain.doFilter(request, response);

        long end = System.currentTimeMillis();
        System.out.println("처리시간 : " + (end - start) + "ms");
    }

}
