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

	request.getCookies()를 통해서 클라이언트 요청에 함께 보낸 쿠키의 값을 배열로 받는다.
 */
@WebServlet("/getCookie")
public class GetCookieServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String username = "알수없음";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("username")) {
					username = c.getValue();
					break;
				}
			}
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("저장된 username의 값 : " + username);
	}

	

}
