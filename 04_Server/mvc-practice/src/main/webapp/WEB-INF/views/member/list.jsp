<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.mvc.model.member.MemberDTO" %>
    
<%--
	View 회원목록화면
	
	Controller에서 request.setAttribute("memberList", list)로 넘긴 데이터를
	아래 코드에서 request.getAttribute("memberList")로 꺼내서 화면에 출력 가능.

 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 목록</h1>
	
	<%-- 
		<p><a href="/mvc/member/insertForm.html">+ 회원 등록</a></p> 
		
		프로젝트의 컨텍스트 경로를 동적으로 출력할 수 있음.
		${pageContext.request.contextPath}
	--%>
	
	<p><a href="${pageContext.request.contextPath}/member/insertForm.html">+ 회원 등록</a></p> 
	
	
	<table border="1" cellpadding="8">
		<thead>
			<tr>
				<th>회원번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>나이</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<%-- 자바코드를 사용할 수 있는 영역 --%>
			<%  
				List<MemberDTO> memberList = (List<MemberDTO>) request.getAttribute("memberList");
			
				if(memberList == null || memberList.isEmpty()) {
			%>
				<tr><td colspan="4">등록된 회원이 없습니다.</td></tr>
			<% 
				} else { 
					for(MemberDTO m : memberList) {
			%>
				<tr>
					<td><%= m.getId() %></td>
					<td><%= m.getName() %></td>
					<td><%= m.getEmail() %></td>
					<td><%= m.getAge() %></td>
					<td>
						<a href="${pageContext.request.contextPath}/member/delete?id=<%=m.getId()%>">
							삭제
						</a>
					</td>
				</tr>
			<% 
					}
				}
			%>
		</tbody>
	</table>
</body>
</html>