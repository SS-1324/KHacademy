package com.kh.demo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/list")
    public String list(){
        return "board/list";
    }

    // --------- 페이지 이동
    @GetMapping("/write")
    public String writeForm(){
        return "board/form";
    }
}
