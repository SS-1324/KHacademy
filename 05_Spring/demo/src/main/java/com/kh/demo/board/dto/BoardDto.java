package com.kh.demo.board.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String memberId; //작성자
    private String category;
    private String title;
    private String content;
    private int count;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    //JSTL표시용
    private String createAtStr;
    private String updateAtStr;

    // member join해서 가져올 값
    private String writerNickname;

    // 상세보기 화면에서 보여줄 첨부이미지 목록 일대다
    private List<BoardImageDto> images;
}
