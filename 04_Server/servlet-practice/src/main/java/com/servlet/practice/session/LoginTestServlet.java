package com.servlet.practice.session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 	세션은 쿠키와 달리 데이터가 "서버"에 저장됨.
 	클라이언트에게는 세션을 구분하기위한 식별자(JSESSIONID)를 쿠키형태로 전달함.
 	그래서 로그인 정보처럼 민감한 정보를 쿠키에 직접적으로 저장하는 것 보다는 세션을 사용하는게 안전하다.
 	
 	request.getSession() : 현재 요청에 연결된 세션이 있으면 반환, 없으면 새로 만들어서 반환.
 							(내부적으로 첫 호출시 JSESSIONID 쿠키가 자동으로 클라이언트에 발급됨)
 */

@WebServlet("/loginTest")
public class LoginTestServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//세션에 로그인 사용자 정보를 저장
		session.setAttribute("loginUser", "최지원");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("로그인 처리 완료(세션에 저장됨.)");
	}

	

}
