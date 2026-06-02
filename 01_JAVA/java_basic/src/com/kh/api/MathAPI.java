package com.kh.api;

public class MathAPI {
	//java.lang -> 기본으로 import된 클래스
	//java.lang.Math : 모든 필드가 상수필드, 모든 메서드가 static메서드
	public void method() {
		System.out.println("PI : " + Math.PI);
		
		System.out.println("랜덤(0.00~0.99) : " + Math.random());
		
		System.out.println("절대값 : " + Math.abs(-10.4));
		
		System.out.println("올림 : " + Math.ceil(10.2));
		
		System.out.println("반올림 : " + Math.round(10.2));
		
		System.out.println("버림 : " + Math.floor(10.2));
	}

}
