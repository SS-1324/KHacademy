package com.kh.demo.board.service;

import com.kh.demo.board.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments(Long boardId);
    CommentDto addComment(Long boardId, String content, String writerId);
    void deleteComment(Long commentId, String requestMemberId);
}
