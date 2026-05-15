package com.kh.loop;

import java.util.Scanner;

public class Break {
	/*
	 	break : 반복문, switch문 안에서 사용되는 분기문
	 	        break;가 실행되는 순간 해당 블럭의 반복문 또는 switch문은 강제 종료됨.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//랜덤값(1~100)을 발생시켜 출력 -> 반복
		//단, 랜덤값이 3의 배수일 경우 반복문을 종료
		
		while(true) {
			int ran = (int)(Math.random() * 100) + 1;
			System.out.println(ran);
			if(ran % 3 == 0) {
				break;
			}
		}
		
		//사용자에게 문자열을 입력받아 해당 문자열의 길이를 출력하는 프로그램을 만들어라.
		//단, 사용자가 "exit"을 입력하면 해당 행위를 중단한다.
		//[console]
		//문자열 입력 : hello
		//길이 : 5
		//문자열 입력 : exit
		//종료합니다...
		
		while(true) {
			System.out.print("문자열 입력 : ");
			String str = sc.next();
			
			if(str.equals("exit")) {
				System.out.println("종료합니다...");
				break;
			}
			
			System.out.println("길이 : " + str.length());
		}
		
	}

}
