package com.kh.control.practice;

import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		// 0점부터 100점 사이의 정수를 입력받아 
		// 아래 조건에 따라 등급(학점)을 출력하는 프로그램을 작성하세요.
		
		// ex) 점수를 입력하세요 : 85
		//     당신의 성적은 B입니다.
		/*
		   90 ~ 100	A
			80 ~ 89	B
			70 ~ 79	C
			60 ~ 69	D
			0 ~ 59	F
		*/
		Scanner sc = new Scanner(System.in);
		
		System.out.print("점수를 입력하세요 :");
		int score = sc.nextInt();
		char grade = 'E';
		
		if(score < 0 || score > 100) {
			System.out.println("성적은 0~100점 사이값으로 입력해주세요.");
		} else if(score >=90) {
			grade = 'B';
		} else if(score >=80) {
			grade = 'C';
		} else if(score >=70) {
			grade = 'D';
		} else if(score >=60) {
			grade = 'E';
		} else {
			grade = 'F';
		}
		
		System.out.printf("당신의 성적은 %c입니다.", grade);
		sc.close();

	}

}
