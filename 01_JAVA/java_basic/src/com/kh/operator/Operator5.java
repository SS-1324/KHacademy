package com.kh.operator;

public class Operator5 {

	/*
	 증감연산자
	 ++ : 변수에 담긴값 1증가
	 -- : 변수에 담긴값 1감소
	 
	 (증감연산자)변수 : 전위연산 -> "선증감" 후처리
	 변수(증감연산자) : 후위연산 -> 선처리 "후증감"
	 */
	public static void main(String[] args) {
		int num = 0;
		
		System.out.println(num++); //0출력 num에 값 : 1
		System.out.println(++num); //2출력 num에 값 : 2
		System.out.println(num--); //2출력 num에 값 : 1
		System.out.println(--num); //0출력 num에 값 : 0

	}

}
