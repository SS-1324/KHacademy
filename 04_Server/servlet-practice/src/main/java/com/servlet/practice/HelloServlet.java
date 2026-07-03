package com.servlet.practice;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 	@단어 : 어노테이션 - 컴파일러에게 전달하는 주석으로 어노테이션마다 컴파일러가 특정 기능을 수행해 줌.
 	
 	기본적인 Servlet 구성
 	- @WebServlet("/hello") : 이 서블릿이 어떤 URL요청을 처리할 것인가 매핑
 	  -> 브라우저에서 http://localhost:8080/프로젝트path/hello 로 접속시 이 서블릿이 실행됨.
 	- HttpServlet를 상속받아야 서블릿 컨테이너가 이 클래스를 서블릿으로 인식함.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HelloServlet() {
        super();
    }


    // get방식으로 요청이 왔을 때 실되는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	HttpServletRequest : 요청시에 전달된 내용들이 담겨있는 객체(사용자가 입력한 값, 요청방식, 요청IP, url...)
		 	HttpServletResponse : 요청에 대한 처리 후 응답할 때 사용하는 객체(어떤타입으로 응답할지, 어떤값을 응답할지등 설정)
		 */
		
		//응답의 컨텐츠 타입
		//이 설정을 하지 않으면 무조건 텍스트로 응답이 됨
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello, Servlet!</h1>");
		out.println("hello요청에대한 doGet()메서드의 응답 화면입니다.");
	}

	// post방식으로 요청이 왔을 때 실되는 메서드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(req, resp);
//	}
	
	

}
