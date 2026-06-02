package com.kh.api;

public class WrapperAPI {
	/*
	 Wrapper 클래스
	 -> 기본 자료형을 객체로 포장해줄 수 있는 클래스
	 
	 boolean(Boolean)
	 char(Character)
	 byte(Byte)
	 ...
	 
	 -> 원시타입을 객체로 표현해야할 경우
	    - 메서드의 매개변수로 전달해야할 때
	    - 원시타입에도 다형성을 적용하고 싶을 때
	 */

	public void method() {
		int num1 = 10;
		int num2 = 20;
		
		System.out.println(num1 == num2);
		
		//1. 객체생성구문을 통해서 클래스타입으로 변경(Boxing)
		Integer i1 = new Integer(num1);
		
		//2. 객체 생성없이 바로 대입도 가능(AutoBoxing)
		Integer i2 = num2;
		
		System.out.println(i1.equals(i2));
		System.out.println(i1.equals(10));
		//compareTo : 두 값을 비교해서 기준값이 크면1, 전달값이 크면-1, 동일하면 0
		System.out.println(i1.compareTo(i2)); 
		
		//문자열을 특정 타입으로 변경하고싶을 때 래퍼클래스.parse자료형(문자열);
		Integer i3 = Integer.parseInt("13");
		Integer i4 = new Integer("20");
		
		//unboxing : wrapper클래스 자료형 -> 기본자료형
		// xxxValue();
		int num3 = i3.intValue();
		int num4 = i4; //autoUnboxing
	}
}
