package com.kh.demo.board.dto;

import lombok.*;

import java.time.LocalDateTime;

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
}
