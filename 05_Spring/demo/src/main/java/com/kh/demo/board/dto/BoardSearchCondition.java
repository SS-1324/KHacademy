package com.kh.demo.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchCondition {
    //검색 파라미터
    // 카테고리 필터 : 전체, 자유, 공지, 질문...
    private String category;
    // 검색어는 자유 없으면 null
    private String keyword;
    // 검색 종류 : titleContent, title, content, writer
    private String searchType;

    //사용자가 보고자하는 페이지(기본 1)
    private int page = 1;

    //페이징: 한페이지에 보여줄 개수
    private int size = 10;

    // service에서 Page정보로 직접 계산을 해서 사용하는 값
    // offset = (page - 1) * size;
    private int offset;
    private int limit;
}
