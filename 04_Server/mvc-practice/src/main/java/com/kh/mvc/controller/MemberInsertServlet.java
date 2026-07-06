package com.kh.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.kh.mvc.model.member.MemberDAO;
import com.kh.mvc.model.member.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 	회원등록
 	
 	insertForm.html에서 post로 회원가입을 요청함
 	-> 전달받은 파라미터를 정리함(데이터가 여러개이거나 db에 있는거면 dto로 변환)
 	-> DAO에게 저장을 위임
 	-> 처리가 끝나면 Redirect로 목록 화면으로 이동
 	
 	Redirect를 사용하는 이유 -> forward로 바로 결과 화면을 보여주면 사용자가 새로고침시 다시 POST요청이 전송되면서 회원 중복생성을 시도함.
 							Redirect를 사용하면 아예다른 요청(화면목록을 보여주는)을 통해서 깔끔하게 화면목록을 보여줌.
 */
@WebServlet("/member/insert")
public class MemberInsertServlet extends HttpServlet {
	private MemberDAO memberDAO = new MemberDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getParameter()의 결과는 무조건 String 이므로 사용하는 값에 따라서 변환이 필요.
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		
		MemberDTO dto = new MemberDTO(name, email, age);
		try {
			memberDAO.insert(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// request.getContextPath() : 프로젝트 경로를 자동으로 붙여줌
		response.sendRedirect(request.getContextPath() + "/member/list");
		
		
	}

}
