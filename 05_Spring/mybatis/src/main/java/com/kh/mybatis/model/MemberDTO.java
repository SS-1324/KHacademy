package com.kh.mybatis.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private int id;
    private String name;
    private String email;
    private int age;
}
