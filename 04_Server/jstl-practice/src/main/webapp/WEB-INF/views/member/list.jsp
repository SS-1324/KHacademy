<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.jstl.model.member.MemberDTO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
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
	
	<p><a href="${pageContext.request.contextPath}/member/insertForm.html">+ 회원 등록</a></p> 
	
	
	<table border="1" cellpadding="8">
		<thead>
			<tr>
				<th>순번</th>
				<th>회원번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>나이</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<%-- c:if로 목록이 비어있는 경우 처리 --%>
			<c:if test="${empty memberList}">
				<tr><td colspan="4">등록된 회원이 없습니다.</td></tr>
			</c:if>
			
			<%--
				c:forEach 반복 출력
				var="변수명" : 반복마다 배열의 값을 순차적으로 받아줄 변수
				items="배열/리스트" : 반복해서 값을 꺼내올 배열
				varStatus="status" : 반복의 순번등 부가정보를 가져올 수 있는 변수
			 --%>
			<c:forEach var="m" items="${memberList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${m.id}</td> 
					<td>${m.name}</td>
					<td>${m.email}</td>
					<td>${m.age}</td>
					<td>
						<a href="${pageContext.request.contextPath}/member/delete?id=${m.id}">
							삭제
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>