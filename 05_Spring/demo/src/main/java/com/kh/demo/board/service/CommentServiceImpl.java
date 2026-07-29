package com.kh.demo.board.service;

import com.kh.demo.board.dto.CommentDto;
import com.kh.demo.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentDto addComment(Long boardId, String content, String writerId) {
        if(content == null || content.isBlank()){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요.");
        }

        CommentDto comment = new CommentDto();
        comment.setBoardId(boardId);
        comment.setMemberId(writerId);
        comment.setContent(content);

        commentMapper.insertComment(comment); //실행 후 생성된 commentId가 채워짐

        return commentMapper.selectCommentById(comment.getCommentId());
    }
}
