<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP File 기본구조</title>
</head>
<body>
    <h1 style="color: red;">로그인한 사용자만 접근할 수 있습니다.</h1>
    <p>${errorMsg}</p>

    <a href="/member/insertForm">회원가입</a>
</body>
</html>