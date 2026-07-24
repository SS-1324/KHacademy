package com.kh.demo.common.dto;


import lombok.Getter;

/*
* 페이징 정보를 계산하고 저장하는 객체
*
* 게시판 목록처럼 데이터가 많을 때 한번에 전부 가져올 필요가 없기 떄문에
* 필요한 만큼만 끊어서 보여줄 수 있게 해주는 것이 페이징.
*
* 화면에 보여줄 개수 size와 현재 페이지번호, 전체 게시글 수 totalCount만 알면 나머지는 계산해서 전달이 가능.
* */
@Getter
public class PageInfo {
    private final int page;       //현재 페이지 번호
    private final int size;       //한페이지에 보여줄 게시글 개수
    private final int totalCount; //전체 게시글 수
    private final int totalPages; //전체 페이지 수
    private final int startPage;  //화면에 보여줄 페이지 번호의 시작
    private final int endPage;    //화면에 보여줄 페이지 번호의 끝
    private final boolean hasPrevGroup; //이전 페이지 그룹이 존재하냐(<버튼)
    private final boolean hasNextGroup; //다음 페이지 그룹이 존재하냐(<버튼)

    private static final int PAGE_GROUP_SIZE = 5; //하단에 한번에 보여줄 페이지 번호 개수

    public PageInfo(int page, int size, int totalCount) {
        this.page = (page < 1) ? 1 : page;
        this.size = size;
        this.totalCount = totalCount;

        // (totalCount + size - 1) / size
        this.totalPages = (totalCount + size - 1) / size;

        // 예 page=7, PAGE_GROUP_SIZE=5 -> 6~10
        this.startPage = ((this.page - 1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE + 1;
        this.endPage = Math.min(startPage + PAGE_GROUP_SIZE - 1, this.totalPages);
        // 예: totalPages = 8

        this.hasPrevGroup = startPage > 1;
        this.hasNextGroup = endPage < totalPages;
    }

    // mybatis 동적쿼리에서 사용하기 위한 값
    public int getOffset(){ return (page - 1) * size;}
}
