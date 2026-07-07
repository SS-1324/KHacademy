<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.MemberDTO" %>
<%-- 
	EL(Expression Language) 
	
	EL은 request.getAttribute(...)같은 자바코드를 ${표현식}와같은 간결한 문장으로 대체해 줌.
	${pageContext.request.contextPath}와 같은 형태
--%>

<%
	request.setAttribute("name", "최지원");
	request.setAttribute("age", 25);
	
	MemberDTO member = new MemberDTO("김수민", 20);
	request.setAttribute("member", member);
	
	java.util.List<String> emptyList = new java.util.ArrayList<>();
	request.setAttribute("emptyList", emptyList);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>4. EL문법</h1>
	
	<h3>1) 스크립틀릿/표현식 vs EL방식 비교</h3>
	<p>표현식 name값 : <%=request.getAttribute("name") %></p>
	<p>EL방식 name값 : ${name}</p>
	<%--el방식으로 작성시 request영역부터 session, application영역을 순차적으로 탐색해서 해당값을 가져옴 --%>
	
	<h3>2) EL의 탐색범위(pageScope -> requestScope -> sessionScope -> applicaionScope)</h3>
	<p>${name} - 범위지정없이 특정 값을 검색시 순차적으로 찾아서 출력</p>
	<p>${sessionScope.name} - 특정 스코프를 명시적으로 지정가능</p>
	
	<h3>3) 객체에서 필드값에 접근(getter)</h3>
	<%-- member.getName(), member.getAge()처럼 getter를 내부적으로 호출함 --%>
	<p>이름 : ${member.name}</p>
	<p>나이 : ${member.age }</p>
	
	<h4>4) EL연산자</h4>
	<p>산술연산 : 1+2 = ${1+2}</p>
	<p>비교연산 : [age >= 20 ? "성인" : "미성년자"] -> ${age >= 20 ? "성인" : "미성년자"}</p>
	<p>empty연산자(list에 값이 비어있는지 확인) : ${empty emptyList}</p>
</body>
</html>











