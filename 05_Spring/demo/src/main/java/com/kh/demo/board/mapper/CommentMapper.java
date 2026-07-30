package com.kh.demo.board.mapper;

import com.kh.demo.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentDto commentDto);
    CommentDto selectCommentById(Long commentId);
    List<CommentDto> selectCommentsByBoardId(Long boardId);
    int deleteComment(Long commentId);
}
