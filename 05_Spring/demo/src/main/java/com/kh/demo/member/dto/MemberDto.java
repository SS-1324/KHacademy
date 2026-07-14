package com.kh.demo.member.dto;

import lombok.*;

import java.time.LocalDateTime;


/*
 *   DTO : 계층간에 데이터를 주고받기위한 전달용 데이터
 *
 *   MemberDto : Member테이블과 1:1로 대응되는 클래스
 * */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberDto {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String nickname;
    private String email;
    private String profile;
    private LocalDateTime createAt;


}
