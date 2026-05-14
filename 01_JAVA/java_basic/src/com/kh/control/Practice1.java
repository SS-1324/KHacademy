package com.kh.control;

import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		/*
		 * 나이를 입력받아서
		 * 13세이하 : 어린이
		 * 14세~19세이하 : 청소년
		 * 20세 이상 : 성인
		 * 
		 * [console]
		 * 나이입력 : 21
		 * 21세는 성인에 속합니다.
		 */

		Scanner sc = new Scanner(System.in);
		System.out.print("나이입력 : ");
		int age = sc.nextInt();
		String grade = "성인";
		
		if(age <= 13) {
			grade = "어린이";
		} else if(age <= 19){
			grade = "청소년";
		} 
		
		System.out.printf("%d세는 %s에 속합니다.", age, grade);
	}

}
