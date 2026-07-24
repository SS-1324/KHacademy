package com.kh.demo.board.controller;

import com.kh.demo.board.dto.BoardDto;
import com.kh.demo.board.dto.BoardListResult;
import com.kh.demo.board.dto.BoardSearchCondition;
import com.kh.demo.board.service.BoardService;
import com.kh.demo.common.SessionConst;
import com.kh.demo.member.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;

/*
* URL 설계
* /board/{boardId} 처럼 변수 하나짜리 패턴을 쓰지않고
* /board/list, /board/write, /board/detail/{boardId} 처럼 각 기능마다
* 고정 문자열(list, write, detail, edit, delete) 세그먼트를 앞에 붙인다.
*
*
* /board/detail/{boardId}, /board/list 쓰는 것 대신
* /board/{boardId}, /board/list 작성해도 출동하지 않는다.
* -> 하지만 URL의 의도를 명확하게 드러내기가 어렵다.
* */

@Controller
@RequestMapping("/board")
public class BoardController {


    @Autowired
    private BoardService boardService;


    @GetMapping("/list")
    public String list(@ModelAttribute BoardSearchCondition condition, Model model){
        //게시글을 조회해서 list페이지로 전달
        BoardListResult result = boardService.getBoardList(condition);
        model.addAttribute("boardList", result.getBoardList());
        model.addAttribute("pageInfo", result.getPageInfo());

        return "board/list";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute BoardDto boardDto,
                        @RequestParam(value = "imageFiles", required = false) List<MultipartFile> images,
                        HttpSession session) throws IOException {

        MemberDto loginMember = (MemberDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        boardDto.setMemberId(loginMember.getMemberId());

        Long boardId = boardService.writeBoard(boardDto, images);

        return "redirect:/board/detail/" + boardId;
    }

    // --------- 페이지 이동
    @GetMapping("/write")
    public String writeForm(){
        return "board/form";
    }
}
