package com.kh.demo.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardImageDto {
    private Long imageId;
    private Long boardId;
    private String originalName;
    private String saveName;
    private String imagePath;
    private int imageOrder;
    private LocalDateTime createAt;
}
