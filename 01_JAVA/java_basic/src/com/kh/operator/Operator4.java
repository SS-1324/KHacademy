package com.kh.operator;

import java.util.Scanner;

public class Operator4 {
	/*
	 논리연산자
	 두개의 논리값을 연산해주는 연산자
	 
	 논리값 && 논리값 : 왼쪽, 오른쪽의 두 조건이 모두 true일 경우 true
	 논리값 || 논리값 : 왼쪽, 오른쪽의 두 조건중 하나라도 true일 경우 true
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//사용자에게 1~100의 숫자를 입력받아
		//정상적으로 입력했는지 알아보고 싶다.
		System.out.print("1~100사이 입력 : ");
		int num = sc.nextInt();
		boolean result = (num >= 1) && (num <= 100);
		System.out.println("사용자가 입력한 값은 1~100 사이 값이다 : " + result);
	
		//사용자에게 알파벳 하나 입력받아서 대소문자를 확인해라.
		System.out.print("문자 하나 입력 : ");
		char ch = sc.next().charAt(0);
		//문자 한개 입력받을 때 sc.next().charAt(0);
		// sc.next() -> 문자열, charAt(0) -> 0번째 글자 하나만 추출
		
		//65~90까지가 대문자
		//97~122까지가 소문자
		boolean res1 = (ch >= 'A' && ch <= 'Z'); //대문자이면 true가 나오도록
		boolean res2 = (ch >= 'a' && ch <= 'z'); //소문자이면 true가 나오도록
		System.out.println("사용자가 입력값이 알파벳이다 : " + (res1 || res2));
	}

}
