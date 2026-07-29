package com.kh.demo.board.service;

import com.kh.demo.board.dto.CommentDto;

public interface CommentService {
    CommentDto addComment(Long boardId, String content, String writerId);
}
