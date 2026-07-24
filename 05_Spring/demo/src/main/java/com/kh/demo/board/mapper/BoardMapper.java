package com.kh.demo.board.mapper;

import com.kh.demo.board.dto.BoardDto;
import com.kh.demo.board.dto.BoardImageDto;
import com.kh.demo.board.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    // useGeneratedKeys는 XML 쪽 <insert>에 설정해서 pk를 가져올 수 있게 해줌
    // insert 실행 후, DB가 dto.boardId에 자동으로 채워짐
    int insertBoard(BoardDto boardDto);

    //파라미터가 하나여도 그게만약 list라면, XML에서 <foreach collection="images">로 접근하려면
    //@Param으로 이름을 명시해 줘야한다.
    void insertBoardImages(@Param("images") List<BoardImageDto> images);

    List<BoardDto> selectBoardList(BoardSearchCondition condition);

    int selectBoardListCount();
}
