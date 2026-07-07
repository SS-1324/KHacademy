package com.kh.jsp;

/*
  	Member - DTO : meber 테이블을 기준으로 조회한 한 행을 표현하는 객체
  	
  	DTO는 Controller <-> DAO <->View 사이에서 데이터를 주고받는 '상장' 역할을 한다.
 * */
public class MemberDTO {
	//필드
	private String name;
	private int age;

	//생성자 - 이 객체를 생성하는 방법에 따라서 여러개 생성
	public MemberDTO() {
		super();
	}

	public MemberDTO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
}
