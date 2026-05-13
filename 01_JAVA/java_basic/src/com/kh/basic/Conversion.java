package com.kh.basic;

public class Conversion {
	/*
	 형변환
	 자료형을 다른 자료형으로 변경하는 것
	 
	 자동형변환
	 연산시에 두개의 항을 하나의 자료형으로 일치시켜야 연산이 가능하다.
	 -> 데이터 손실이 적은 방향으로 컴파일러가 직접 형변환을 해준다.
	 
	 명시적형변환
	 자동형변환이 되지 않는 조건에서 개발자가 직접 진행하는 것(데이터 손실 가능)
	 */

	public static void main(String[] args) {
		double pi = 3.1415;
		int num = (int)pi;
		System.out.println(num);
		
		//long -> int 변환시 메모리 기준으로 자르기 때문에 연관성이 없는 숫자가 나올 수 있다.
		long longNum = 3000000000000000007L;
		int intNum = (int)longNum;
		System.out.println(intNum);

		char ch = 'a';
		System.out.println("a : " + ch);
		System.out.println("a : " + (int)ch);
		System.out.println("b : " + (ch + 1));
		System.out.println("b : " + (char)(ch + 1));
		System.out.println("b : " + (char)(98));
	}

}
