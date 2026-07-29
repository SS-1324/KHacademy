package com.kh.demo.board.controller;

import com.kh.demo.board.dto.CommentDto;
import com.kh.demo.board.dto.CommentRequest;
import com.kh.demo.board.service.CommentService;
import com.kh.demo.common.SessionConst;
import com.kh.demo.common.dto.ApiResponse;
import com.kh.demo.member.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
    @RestController = @Controller + @RequestBody
    이 클래스의 모든 메서드는 View이름이 아니라 JSON으로 데이터를 응답하겠다.
* */

@RestController
@RequestMapping("/api")
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    /*
    * 응답시 커스텀 응답인 ApiResponse만 반환하면 spring은 항상 200 ok로 상태코드를 응답한다.
    * ResponseEntity는 Http응답의 3가지요소 상태코드, 헤더, 바디 적절하게 응답하기위한 표준객체
    * */
    @PostMapping("/board/{boardId}/comment")
    public ResponseEntity<ApiResponse<CommentDto>> addComment(@PathVariable Long boardId,
                                                             @RequestBody CommentRequest request,
                                                             HttpSession session){
        MemberDto loginMember = (MemberDto) session.getAttribute(SessionConst.LOGIN_MEMBER);

        try {
            CommentDto saved = commentService.addComment(boardId, request.getContent(), loginMember.getMemberId());
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(saved));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
        }
    }

}
