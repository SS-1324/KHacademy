package com.kh.demo.board.service;

import com.kh.demo.board.dto.BoardDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long writeBoard(BoardDto boardDto, List<MultipartFile> images) throws IOException;
}
