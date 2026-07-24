package com.kh.demo.board.dto;

import com.kh.demo.common.dto.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardListResult {
    private List<BoardDto> boardList;
    private PageInfo pageInfo;
}
