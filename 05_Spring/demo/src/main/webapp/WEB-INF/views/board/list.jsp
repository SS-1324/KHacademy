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
            <div class="board-table-wrap">
                <table class="board-table">
                    <thead>
                        <tr>
                            <th class="board-table_col-no">번호</th>
                            <th class="board-table_col-category">카테고리</th>
                            <th class="board-table_col-title">제목</th>
                            <th class="board-table_col-writer">작성자</th>
                            <th class="board-table_col-date">작성일</th>
                            <th class="board-table_col-count">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="board" items="${boardList}" varStatus="status">
                            <tr>
                                <td class="board-table_col-no">${pageInfo.totalCount - (pageInfo.page - 1) * pageInfo.size - status.index}</td>
                                <td class="board-table_col-category"><span class="board-table_category">${board.category}</span></td>
                                <td class="board-table_col-title">${board.title}</td>
                                <td class="board-table_col-writer">${board.writerNickname}</td>
                                <td class="board-table_col-date">${board.createAtStr}</td>
                                <td class="board-table_col-count">${board.count}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
    <nav class="pagenation">
        <c:if test="${pageInfo.hasPrevGroup}">
            <a class="pagenation-item" href="/board/list?page=${pageInfo.startPage - 1}"><<</a>
        </c:if>
        <c:forEach var="p" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
            <a class="pagenation-item ${p == pageInfo.page ? 'pagenation-item_active' : ''}" href="/board/list?page=${p}">${p}</a>
        </c:forEach>
        <c:if test="${pageInfo.hasNextGroup}">
            <a class="pagenation-item" href="/board/list?page=${pageInfo.endPage + 1}">>></a>
        </c:if>
    </nav>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />