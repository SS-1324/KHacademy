<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <h2 class="page-title">게시글 작성</h2>

    <input type="hidden" id="board-key" value="${board.boardId}">
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

    <!-- 댓글 영역 -->
    <section class="comment-section">
        <h3 class="comment-section_title">댓글 7</h3>
        <ul class="comment-list" id="comment-list">
            <li>
                <div class="comment-list_body">
                    <span class="comment-list_writer">user01</span>
                    <span class="comment-list_content">안녕하세요.</span>
                    <span class="comment-list_date">xxxx년 x월 x일</span>
                </div>
                <button type="button" class="btn btn-outline">삭제</button>
            </li>
            <li>
                <div class="comment-list_body">
                    <span class="comment-list_writer">user01</span>
                    <span class="comment-list_content">안녕하세요.</span>
                    <span class="comment-list_date">xxxx년 x월 x일</span>
                </div>
                <button type="button" class="btn btn-outline">삭제</button>
            </li>
        </ul>

        <c:choose>
            <c:when test="${not empty loginMember}">
                <form class="comment-form" id="comment-form">
                    <textarea placeholder="댓글입력..." name="content" rows="2" required></textarea>
                    <button type="submit" class="btn btn-primary">등록</button>
                </form>
            </c:when>
            <c:otherwise>
                <p class="form-tip"><a href="/member/login">로그인</a> 후 댓글을 작성하세요.</p>
            </c:otherwise>
        </c:choose>
    </section>

    <!--
    댓글 목록에 각 댓글을 그릴 때 가져다 사용하기위해서 저장해둔 임시 UI
    -->
    <template id="comment-template">
        <li>
            <div class="comment-list_body">
                <span class="comment-list_writer"></span>
                <span class="comment-list_content"></span>
                <span class="comment-list_date"></span>
            </div>
            <button type="button" class="btn btn-outline">삭제</button>
        </li>
    </template>

    <script src="/js/board.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />