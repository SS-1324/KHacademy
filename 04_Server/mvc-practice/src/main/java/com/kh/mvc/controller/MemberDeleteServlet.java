package com.kh.mvc.controller;

import java.io.IOException;

import com.kh.mvc.model.member.MemberDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 	list.jsp로부터 삭제링크로 url?id=숫자 GET요청으로 ID를 전달함
 	-> id를 가져와서 삭제 후 member/list화면으로 전달
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private MemberDAO memberDAO = new MemberDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		memberDAO.delete(id);
		
		response.sendRedirect(request.getContextPath() + "/member/list");
	}

}
