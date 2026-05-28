package com.kh.inherit;

public class Run {
	/*
	 	상속이란
	 	부모 클래스의 필드와 메서드를 자식클래스가 물려받아 사용하는 것.
	 	-> 자식 객체생성시 -> 부모클래스 생성자 실행 -> 자식클래스 생성자 실행
	 	-> 부모부분과 자식부분이 순차적으로 메모리구조를 만듬(자식객체 내부에는 부모객체의 메모리공간이 존재)
	 	
	 	장점 
	 	재사용성 / 생산성 증가, 유지보수성 증가
	 	
	 	특징
	 	단일상속 : 다중상속은 불가(부모는 하나)
	 */

	public static void main(String[] args) {
		Man m1 = new Man("최지원");
		m1.tellYourName();
		
		BusinessMan m2 = new BusinessMan("김수민", "KH", "매니저");
		m2.tellYourName();

	}

}
