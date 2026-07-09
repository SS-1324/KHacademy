package com.kh.spring.member;

import lombok.*;

@Getter //필드에있는 모든 변수의 getter를 자동생성
@Setter //필드에있는 모든 변수의 setter를 자동생성
@NoArgsConstructor //기본생성자 자동생성
@AllArgsConstructor //모든 필드를 초기화하는 생성자 자동생성
@ToString //toString자동생성
@EqualsAndHashCode //equest, hashcode자동생성
public class MemberDTO {
    private int id;
    private String name;
    private String email;
    private int age;
}
