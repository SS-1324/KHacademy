package com.kh.operator;

import java.util.Scanner;

public class Operator6 {
	
	/*
	 삼항연산자
	 (조건식) ? (조건식이true인경우) : (조건식이false경우)
	 
	 조건식은 반드시 true 또는 false를 반환해야함.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = (3 > 10) ? 10 : 20;
		System.out.println(num);
		
		//입력받은 정수값이 양수인지 아닌지 판별해서 출력.
		//정수값 입력 : 
		// 양수 / 음수
		
		System.out.print("정수값 입력 : ");
		int number = sc.nextInt();
		
		String result = (number > 0) ? "양수" : "음수";
		result = (number == 0) ? "0" : ((number > 0) ? "양수" : "음수");
		System.out.println(result);
		
		
	}

}
