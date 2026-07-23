package com.kh.demo.board.service;

import com.kh.demo.board.dto.BoardDto;
import com.kh.demo.board.dto.BoardImageDto;
import com.kh.demo.board.mapper.BoardMapper;
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
