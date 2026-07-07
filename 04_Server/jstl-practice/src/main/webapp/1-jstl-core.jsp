<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.jstl.model.member.MemberDTO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Core Library</h1>
	
	<h3>1. 변수</h3>
	
	<c:set var="num1" value="10" />
	<%-- pageContext.setAttribute("num1", 10)과 동일 --%>
	num1 : ${num1} <br>
	
	<%-- scope속성을 통해 저장범위를 지정할 수 있음 --%>
	<c:set var="num2" value="20" scope="request" />
	num2 : ${num2} <br>
	
	<c:set var="result" value="${num1 + num2}" scope="session" />
	result 값 : ${result} <br>
	
	<%-- 삭제시 c:remove사용하고 scope를 지정하지 않으면 모든 스코프에 해당 값이 삭제됨 --%>
	<c:remove var="result" scope="request" />
	request영역 result 값 삭제 : ${result} <br>
	
	<c:remove var="result" />
	모든영역 result 값 삭제 : ${result} <br>
	
	<%-- c:out을 통해서 값을 출력할 수 있음 --%>
	result 값 : ${result} <br>
	result 값 : <c:out value="${result}"/> <br>
	result 값 : <c:out value="${result}" default="없음"/> <br>
	
	<br>
	
	<h3>2. 조건문 - if(c:if test="조건식")</h3>
	
	<%if(10>20) { %>
		<strong>num2가 num1보다 큽니다.</strong><br>
	<%} %>
	
	<c:if test="${num1 > num2}">
		<strong>num1가 num2보다 큽니다.</strong><br>
	</c:if>
	
	<c:if test="${num1 < num2}">
		<strong>num2가 num1보다 큽니다.</strong><br>
	</c:if>
	
	<%-- el에서 비교할 때 문자열은 ==비교가 가능 --%>
	<c:set var="str1" value="hello" />
	<c:if test="${str1 == 'hello'}">
		<strong>${str1}</strong><br>
	</c:if>
	
	<h3>3. 조건문 - 다중분기(c:choose / c:when / c:otherwise)</h3>
	<c:choose>
		<c:when test="${num1 > 30}">
			<strong>num1이 30보다 큽니다</strong> <br>
		</c:when>
		<c:when test="${num1 > 20}">
			<strong>num1이 20보다 큽니다</strong> <br>
		</c:when>
		<c:when test="${num1 > 9}">
			<strong>num1이 9보다 큽니다</strong> <br>
		</c:when>
		<c:otherwise>
			<strong>모든 조건에 부합하지 않습니다.</strong> <br>
		</c:otherwise>
	</c:choose>
	
	<h3>4. 반복문 - forEach </h3>
	<p>
		- 카운터반복
		- 배열/컬렉션 반복
	</p>
	
	<%-- 카운터 반복 --%>
	<c:forEach var="i" begin="1" end="10" step="2"> <%-- i는 1부터 10까지 2씩 증가하며 반복 --%>
		반복 : ${i} <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="6"> 
		<h${i}>태그별 제목 크기</h${i}>
	</c:forEach>
	
	<%
		ArrayList<MemberDTO> list = new ArrayList<>();
		list.add(new MemberDTO(1, "최지원", "user01@gmail.com", 20));
		list.add(new MemberDTO(2, "최지투", "user02@gmail.com", 22));
		list.add(new MemberDTO(3, "최지삼", "user03@gmail.com", 24));
	%>
	<c:set var="memberList" value="<%=list%>" scope="request" />
	
	<table>
		<thead>
			<th>회원번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>나이</th>
		</thead>
		<tbody>
			<%-- 배열/컬렉션 반복 --%>
			<c:forEach var="m" items="${memberList}">
				<tr>
					<td>${m.id}</td>
					<td>${m.name}</td>
					<td>${m.email}</td>
					<td>${m.age}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>










