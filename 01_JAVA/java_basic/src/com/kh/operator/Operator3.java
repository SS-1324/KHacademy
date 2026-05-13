package com.kh.operator;

public class Operator3 {

	/*
	 비교연산자
	 - 대소비교 : <, >, <=, >=
	 - 동등비교 : ==, !=
	 
	 원시타입
	 - 정수, 실수, 문자등의 기본변수는 ==, !=등으로 비교가 가능
	 
	 객체비교(String)
	 - String같은 객체는 참조형이라서 ==로 비교시 문제가 발생한다.
	 - 문자열1 == 문자열2 -> 문자열1.equals(문자열2)
	   (객체타입은 실제값을 가지고 있지않고 메모리주소를 가지고 있어서 비교시 주소값이 비교됨)
	 */
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		System.out.println("a == b : " + (a == b));
		System.out.println("a <= b : " + (a <= b));
		
		System.out.println((a * 2) < (b * 4));
		
		String str1 = "hello";
		String str2 = "hello";
		System.out.println(str1 == str2);
		//자바의 문자열 리터럴은 String Pool이라는 메모리 영역에 저장되고
		// 동일한 리터럴을 참조하게되면, 기존에 저장된 상수값의 메모리를 재사용함.
		// => 한번 리터럴 메모리에 값을 올려두면 재사용해서 효율성을 높인다.
		
		String str3 = new String("hello");
		System.out.println(str2 == str3);
		//일반적으로 객체는 생성시마다 각각 다른 메모리를 가지고있고
		//변수에는 해당 메모리에 주소가 저장되기 때문에
		//값이 동일하더라도 ==, !=비교시 주소값이 비교되어 false가 된다.
		
		System.out.println(str2.equals(str3));
		//각 객체마다 비교기준이 정의되어있는 equals함수를 사용해 비교해야 한다.
		
	}

}
