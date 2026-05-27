package com.kh.option;

public class Run {
	/*
	 pakage
	 클래스간 공간적 충돌이나 접근방법의 충돌을 막기위해
	 저장위치를 구분해서 명확하게 접근할 수 있도록 해주는 개념
	 
	 네이밍방식
	 도메인 : www.kh.com 일때
	 패키지 : com.kh.팀명, com.kh.프로젝트명 ....
	 
	 static 메서드
	 인스턴스 변수 없이 클래스 단위로 호출이 가능.
	 일반적으로 객체상태와 무관하게 동작하는 기능 구현시 사용
	 인스턴스변수, 인턴스메서드에 접근 불가
	 
	 static 변수
	 클래스에 소속된 모든 인스턴스가 공유하는 변수, 한 클래스에서 공통적인 값을 유지할 때 사용.
	 -> 일반적으로 final과 함께 많이 사용.
	 */

	public static void main(String[] args) {
		Counter.showCount(); //static메서드
		
		Counter c1 =  new Counter("첫번째");
		Counter c2 =  new Counter("두번째");
		Counter c3 =  new Counter("세번째");
		Counter.showCount();
		c1.printCount();
		c2.printCount();
		c3.printCount();
	}

}
