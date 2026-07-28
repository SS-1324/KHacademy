<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <h2 class="page-title">게시글 작성</h2>

    <article class="board-detail">
        <header>
            <span>${board.category}</span>
            <h2>${board.title}</h2>
            <div>
                <span>${board.writerNickname}</span>
                <span>${board.createAtStr}</span>
                <span>조회 ${board.count}</span>
            </div>
        </header>

        <div class="board-detail_content">${board.content}</div>

        <c:if test="${not empty board.images}">
            <ul class="board-image-list">
                <c:forEach var="img" items="${board.images}">
                    <li><img src="${img.imagePath}" alt="${img.originalName}"> </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${isOwner}">
            <div class="board-detail_actions">
                <a class="btn btn-outline" href="/board/edit/${board.boardId}">수정</a>
                <form action="/board/delete/${board.boardId}" method="post"
                    onsubmit="return confirm('게시글을 삭제하시겠습니까?')">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </form>
            </div>
        </c:if>

    </article>


    <script src="/js/board.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />