package com.servlet.practice.responsetype;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 	Redirect - 클라이언트에게 이 주로소 다시 요청래! 라고 지시하는 방식
 	
 	새로운 요청이 발생하는 것이므로, 이전 요청에서 request객체에 담아둔 데이터는 사라짐.
 	클라언트 - 서버 간 요청이 2번(최초요청 -> 재요청) 발생하고, 실제로 URL도 변경됨.
 */
@WebServlet("/redirectTest")
public class RedirectTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이 데이터는 request(요청)객체에 담는 데이터이므로 해당 요청이 끝나면 사라짐.
		request.setAttribute("message", "redirect로 전달된 데이터입니다.");
		
		//요청을 보낸 클라이언트에게 result.jsp로 요청을 다시 보내줘라고 응답 -> 브라우저 재요청
		response.sendRedirect("result.jsp");
	}



}
