package com.servlet.practice.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
  쿠키생성
  
  쿠키는 서버가 만들어서 클라이언트(브라우저)에게 전달하고, 브라우저는 이후 요청마다
  이 쿠키를 자동으로 함께 실어서 서버로 보낸다. 즉 "데이터가 클라이언트 쪽에 저장"된다.
 */
@WebServlet("/setCookie")
public class SetCookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키생성 : (key, value)
		Cookie cookie = new Cookie("username", "jiwon");
		cookie.setMaxAge(60 * 60); // 쿠키 유효 시간(초), 60 * 60 = 1시간. MaxAge설정 안할시 브라우저 종료시 삭제됨.
		cookie.setPath("/"); // 이 경로 이하 모든 요청에서 쿠키가 함께 전송되도록 설정
		
		response.addCookie(cookie);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("쿠키 저장 완료(username = jiwon)");
	}

	

}
