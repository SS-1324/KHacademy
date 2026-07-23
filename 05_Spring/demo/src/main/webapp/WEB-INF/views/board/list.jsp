<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <h2 class="page-title">게시판</h2>
    <h4 class="text-right"><a class="btn btn-outline" href="/board/write">글쓰기</a></h4>
    <form class="search-bar">
        <select name="category" class="search-bar__select">
            <option value="전체">전체</option>
            <option value="자유">자유</option>
            <option value="질문">질문</option>
            <option value="공지">공지</option>
        </select>
        <select name="searchType" class="search-bar__select">
            <option value="titleContent">제목+내용</option>
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="writer">작성자</option>
        </select>
        <input type="text" class="search-bar__input" name="keyword" placeholder="검색어를 입력하세요.">
        <button type="submit" class="btn btn-primary">검색</button>
    </form>

    <c:choose>
        <c:when test="${empty boardList}">
            <p>등록된 게시글이 없습니다.</p>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />