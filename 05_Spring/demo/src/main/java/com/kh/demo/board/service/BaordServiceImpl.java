package com.kh.demo.board.service;

import com.kh.demo.board.dto.BoardDto;
import com.kh.demo.board.dto.BoardImageDto;
import com.kh.demo.board.dto.BoardListResult;
import com.kh.demo.board.dto.BoardSearchCondition;
import com.kh.demo.board.mapper.BoardMapper;
import com.kh.demo.common.dto.PageInfo;
import com.kh.demo.common.util.FileUploadUtil;
import com.kh.demo.common.util.SavedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaordServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Value("${file.upload-dir.board}")
    private String boardUploadDir;

    @Override
    public Long writeBoard(BoardDto boardDto, List<MultipartFile> images) throws IOException {
        boardMapper.insertBoard(boardDto); //실행 후 boardDto의 boardId는 자동으로 채워짐
        saveImages(boardDto.getBoardId(), images);

        return boardDto.getBoardId();
    }

    @Override
    public BoardListResult getBoardList(BoardSearchCondition condition) {
        //전체 개수
        int totalCount = boardMapper.selectBoardListCount(condition);

        //페이징 정보를 계산하고 저장하기위한 PageInfo 객체 생성
        PageInfo pageInfo = new PageInfo(condition.getPage(), condition.getSize(), totalCount);

        // PageInfo에서 계산한 값을 검색조건 객체에 담아줌
        condition.setOffset(pageInfo.getOffset());
        condition.setLimit(pageInfo.getSize());

        List<BoardDto> boardList = boardMapper.selectBoardList(condition);
        return new BoardListResult(boardList, pageInfo);
    }

    @Override
    public BoardDto getBoardDetail(Long boardId) {
        //상세페이지 진입시 조회수 + 1
        boardMapper.increaseViewCount(boardId);

        BoardDto board = boardMapper.selectBoardDetail(boardId);

        if (board == null){
            throw new IllegalStateException("존재하지 않는 게시글 입니다.");
        }

        board.setImages(boardMapper.selectImagesByBoardId(boardId));

        return board;
    }

    @Override
    public void updateBoard(Long boardId, BoardDto boardDto, List<MultipartFile> newImages, String requestMemberId) throws IOException {
        BoardDto original = boardMapper.selectBoardDetail(boardId);
        validateOwner(original, requestMemberId);

        boardDto.setBoardId(boardId);
        boardMapper.updateBoard(boardDto);

        boolean hasNewImages = newImages != null && !newImages.isEmpty();
        if(hasNewImages){
            deleteImageFiles(boardMapper.selectImagesByBoardId(boardId));
            boardMapper.deleteImagesByBoardId(boardId);
            saveImages(boardId, newImages);
        }
    }

    private void deleteImageFiles(List<BoardImageDto> images){
        for(BoardImageDto img : images){
            fileUploadUtil.delete(img.getImagePath(), boardUploadDir);
        }
    }

    //게시글의 유효성 체크 함수
    private void validateOwner(BoardDto board, String requestMemberId){
        if(board == null){
            throw new IllegalArgumentException("존재하지 않는 게시글 입니다.");
        }

        if(board.getMemberId() == null || !board.getMemberId().equals(requestMemberId)){
            throw new SecurityException("본인이 작성한 게시글만 수정/삭제할 수 있습니다.");
        }
    }

    private void saveImages(Long boardId, List<MultipartFile> images) throws IOException {
        if(images == null || images.isEmpty()){
            return;
        }

        List<BoardImageDto> imageDtos = new ArrayList<>();
        int order = 0;
        for(MultipartFile file : images){
            SavedFile saved = fileUploadUtil.save(file, boardUploadDir, "/uploads/board");
            if (saved == null){
                continue;
            }
            imageDtos.add(new BoardImageDto(null, boardId, saved.getOriginalName(), saved.getSaveName(), saved.getPath(), order++, null));
        }

        boardMapper.insertBoardImages(imageDtos);
    }
}
