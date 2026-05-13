package com.kh.operator;

public class Operator2 {

	/*
	 산술연산자
	 + - * / %
	 
	 복합대입연산자
	 산술연산후 대입연산을 사용할 때는 축약가능하다.
	 += -= *= /= %=
	 */
	public static void main(String[] args) {
		int a = 5;
		int b = 12;
		
		System.out.println("a + b = " + (a + b));
		System.out.println("a - b = " + (a - b));
		System.out.println("a / b = " + (a / b)); // 0으로 나눌시 에러
		System.out.println("a * b = " + (a * b));
		System.out.println("a % b = " + (a % b));
		
		//a = a + b; == a += b;

	}

}
