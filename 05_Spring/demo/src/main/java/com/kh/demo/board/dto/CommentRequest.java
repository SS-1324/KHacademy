package com.kh.demo.board.dto;

import lombok.Getter;
import lombok.Setter;

/*
    js에서는 아래와같은 방식으로 데이터를 전달함.
    JSON.stringify({content : "안녕하세요"})

    컨트롤러에서 @RequestBody로 받으면, Jackson(JSON변환 라이브러리)이
    JSON문자열의 content키를 이 클래스의 content 필드로 변환해준다.
* */

@Getter
@Setter
public class CommentRequest {
    private String content;
}
