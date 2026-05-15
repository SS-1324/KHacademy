package com.kh.loop;

import java.util.Scanner;

public class Practice4 {

	public static void main(String[] args) {
		// 문자열.length() -> 문자열의 길이를 반환
		//System.out.println("안녕하세요 저는 최지원입니다.".length());
		
		//사용자에게 문자열을 입력받아 해당 문자열의 짝수자리 글자만 출력
		//
		// [console]
		// 문자열 입력 : Hello
		// el
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 입력 : ");
		String str = sc.next();
		
		for(int i=0; i<str.length(); i++) {
			if(i % 2 == 1) { //짝수만은 컴퓨터 기준으로 0부터 카운트
				System.out.print(str.charAt(i));
			}
		}
	}

}
