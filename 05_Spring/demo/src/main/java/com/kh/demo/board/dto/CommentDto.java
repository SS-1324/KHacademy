package com.kh.demo.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long commentId;
    private Long boardId;
    private String memberId;
    private String content;
    private LocalDateTime createAt;

    private String writerNickname;
    private String createAtStr;
}
