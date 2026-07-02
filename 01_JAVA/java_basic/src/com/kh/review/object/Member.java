package com.kh.review.object;
/*
  클래스(class) : 객체를 만들기 위한 설계도
  객체(Object) : 클래스로 만든 실제 값(=인스턴스)
  
  클래스 = 붕어빵 틀, 객체 = 붕어빵
  클래스라는 틀은 하나지만, 그틀로 만들 수 있는 실제 객체(인스턴스)는 여러개.
  각 객체는 서로 독립적인 데이터를 가짐.
 */
public class Member {
	//필드 - 데이터 - 변수 (무조건 private)
	private String memberId;
	private String memberName;
	private int age;
	
	// 생성자(초기화를 위한 특수함수)
	// 객체가 new 만들어지는 순간 자동으로 실행되는 특수 함수
	// -> 객체를 만들면서 필드에 데이터를 초기값으로 세팅하기 위함
	
	// 매개변수가 없는 생성자 -> 기본생성자
	// 생성자가 한개도 없을 때는 자동으로 기본생성자가 생성.
	Member() {
		super(); //super() -> 부모생성자
	}

	//매개변수 있는 생성자 - 생성자는 여러개 만들 수 있다
	// (오버로딩 - 함수명이 동일할 때 매개변수의 타입, 개수가 다른면 다른 함수로 인식됨)
	Member(String memberId, String memberName, int age) {
		super();
		// this.필드 = 내 필드에 있는 매개변수를 호출
		// this를 안쓰면 필드명과 매개변수명이 같을 때 구분이 안됨.
		this.memberId = memberId;
		this.memberName = memberName;
		this.age = age;
	}

	//메서드(클래스안에 있는 함수)
	
	// Getter/ Setter 
	// private으로 막아둔 필드의 값을 외부에서 접근하게 해주는 통로
	// getter : 필드값을 꺼내줌(읽기) , get필드명();
	// setter : 필드값을 바꿈(쓰기), 여기에 함부로 값을 수정하지 못하게 코드를 구성한다. set필드명()
	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public int getAge() {
		return age;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setAge(int age) {
		if(age < 0) {
			System.out.println("나이는 음수가 될 수 없습니다.");
			return;
		}
		
		this.age = age;
	}
	
	// 일반적인 메서드
	// 접근제한자 리턴타입 메서드명(매개변수...){함수 실행시 실행할 코드}
	// 필드를 가지고 뭔가를 하는 기능
	public void introduce() {
		System.out.println("안녕하세요. 저는 " + memberName + "입니다.");
	}

	// toString()
	// 오버라이딩 : 부모객체에 있는 메서드를 내용만 재정의 하는 것, 리턴타입 함수명 매개변수 전부 일치
	// system.out.priont(객체) 이 때 객체의 주소값을 출력하는 것 대신 toString메서드가 호출됨
	// 오버라이딩을 하지 않으면 Obejct에 있는 메서드가 호출되면서 Member@1b2da~ 알아보기 힘든 값이 나옴.
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", age=" + age + "]";
	}
}
