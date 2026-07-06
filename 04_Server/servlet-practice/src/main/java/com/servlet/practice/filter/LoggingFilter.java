package com.servlet.practice.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class LoggingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		long start = System.currentTimeMillis();
		System.out.println("요청 시작 : " + start + "ms");
		
		// 다음 필터 또는 실제 요청하려던 서블릿으로 요청을 전달.
		chain.doFilter(arg0, arg1);
		
		long end = System.currentTimeMillis();
		System.out.println("요청 완료 : " + end + "ms");
		System.out.println("요청 처리 시간 : " + (end - start) + "ms");
		
	}

}
