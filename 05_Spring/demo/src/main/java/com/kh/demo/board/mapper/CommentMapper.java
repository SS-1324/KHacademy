package com.kh.demo.board.mapper;

import com.kh.demo.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insertComment(CommentDto commentDto);
    CommentDto selectCommentById(Long commentId);
}
