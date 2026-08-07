<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<jsp:include page="/WEB-INF/views/common/header.jsp" />
  <section class="hero">
    <h1 class="hero-title">커뮤니티에 오신것을 환영합니다</h1>
    <p class="hero-desc">자유롭게 글을 쓰고 이야기를 나눠보세요.</p>
  </section>

    <!--
        NASA APOD(오늘의 천체 사진) API를 두 가지 방식으로 호출
        1) 서버에서 호출 - HomeController에서 index.jsp진입시 담아서 넘겨준다.
        2) 프론트(브라우저)에서 호출 - 사용자가 날짜를 입력하면 js가
            NASA API를 호출하도록 한다.

    -->
    <section class="apod-section">
        <h2 class="apod-section_title">오늘의 우주사진 <span>백엔드 호출</span></h2>
        <p class="apod-section_desc">서버가 대신 NASA API를 호출함.</p>

        <div class="apod-card">
            <img src="${apod.url}" alt="${apod.title}" class="apod-card_img">
            <h3 class="apod-card_title">
                ${apod.title}
                <span class="apod-card_date">${apod.date}</span>
            </h3>
            <p class="apod-card_desc">${apod.explanation}</p>
        </div>
    </section>
    <section class="apod-section">
        <h2 class="apod-section_title">날짜로 찾아보기 <span>JS로 직접 호출</span></h2>
        <p class="apod-section_desc">브라우저에서 js로 직접 NASA API를 호출함.</p>
        <div class="apod-search">
            <input type="date" id="apod-date-input">
            <button type="button" id="apod-search-btn" class="btn btn-primary">조회</button>
        </div>
        <div id="apod-client-reuslt" class="apod-card"></div>
    </section>
    <script src="/js/nasa-apod.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />