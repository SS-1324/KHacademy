package com.servlet.practice.param;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
	요청 파마라미터 처리
	webapp/join.html의 form태그에서 전달되는 데이터를 받는 서블릿
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//form의 input name="name", name="email" 값을 각각 요청객체에서 꺼낼 수 있다.
		String name = request.getParameter("name");
		String email = request.getParameter("email"); 
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(name + "님, 가입이 완료되었습니다.(이메일 : "+email+")");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email"); 
		
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().println("GET방식 : " + name + "님, 가입이 완료되었습니다.(이메일 : "+email+")");
	}
	
	

}
