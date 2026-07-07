package com.kh.jstl.model.member;

/*
  	Member - DTO : meber 테이블을 기준으로 조회한 한 행을 표현하는 객체
  	
  	DTO는 Controller <-> DAO <->View 사이에서 데이터를 주고받는 '상장' 역할을 한다.
 * */
public class MemberDTO {
	//필드
	private int id;
	private String name;
	private String email;
	private int age;

	//생성자 - 이 객체를 생성하는 방법에 따라서 여러개 생성
	public MemberDTO() {
		super();
	}

	// 조회결과는 모든 값을 가지고와서 사용
	public MemberDTO(int id, String name, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
	}
	// 회원을 추가할 경우 ID는 DB에서 생성하기 때문에 id를 제외한 생성자
	public MemberDTO(String name, String email, int age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}

	//메서드
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
