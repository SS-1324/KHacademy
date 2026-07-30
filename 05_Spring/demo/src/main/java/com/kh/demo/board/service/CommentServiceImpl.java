package com.kh.demo.board.service;

import com.kh.demo.board.dto.CommentDto;
import com.kh.demo.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDto> getComments(Long boardId) {
        return commentMapper.selectCommentsByBoardId(boardId);
    }

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

    @Override
    public void deleteComment(Long commentId, String requestMemberId) {
        CommentDto comment = commentMapper.selectCommentById(commentId);
        if(comment == null) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }

        if(comment.getMemberId() == null || !comment.getMemberId().equals(requestMemberId)){
            throw new SecurityException("본인이 작성한 댓글만 삭제 가능합니다.");
        }

        commentMapper.deleteComment(commentId);
    }
}
