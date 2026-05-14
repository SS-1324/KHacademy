package com.kh.control.practice;

import java.util.Scanner;

public class Practice3 {
	/*
	 	어린이, 청소년, 성인의 구분에 따라 입장료가 다르게 부과되는 
	 	놀이공원 요금 계산기를 만들어보세요.
		또한, 주말에는 20% 할인이 적용됩니다.
		
		### 요금표
		| 구분 | 나이 범위 | 기본 요금 |
		| --- | --- | --- |
		| 어린이 | 0 ~ 12세 | 5,000원 |
		| 청소년 | 13 ~ 18세 | 7,000원 |
		| 성인 | 19세 이상 | 10,000원 |
		
		[console]
		나이를 입력하세요 : 14
		요일을 입력하세요 : 토
		
		청소년 요금입니다. (주말 할인 적용)
		최종 요금은 5600원입니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이를 입력하세요 : ");
		int age = sc.nextInt();
		
		System.out.print("요일을 입력하세요 : ");
		String day = sc.next();
		String grade;
		int fee = 0;
		
		//나이에 따른 변경점
		if(age <= 12) {
			fee = 5000;
			grade = "어린이";
		} else if(age <= 18) {
			fee = 7000;
			grade = "청소년";
		} else {
			fee = 10000;
			grade = "성인";
		}
		
		//주말할인 0.2
		if(day.equals("토") || day.equals("일")) {
			fee = (int)(fee * 0.8);
			System.out.println(grade + " 요금입니다. (주말 할인 적용)");
		} else {
			System.out.println(grade + " 요금입니다.");
		}
		
		System.out.println("최종요금은 " + fee + "원입니다.");
		
		/*
		if(age <= 12) {
			if(day.equals("토") || day.equals("일")) {
				System.out.println("어린이 요금입니다.(주말 할인 적용)");
				System.out.println("최종요금은 4000원입니다.");
			} else {
				System.out.println("어린이 요금입니다.");
				System.out.println("최종요금은 5000원입니다.");
			}
		} else if(age <= 18) {
			if(day.equals("토") || day.equals("일")) {
				System.out.println("청소년 요금입니다.(주말 할인 적용)");
				System.out.println("최종요금은 5600원입니다.");
			} else {
				System.out.println("청소년 요금입니다.");
				System.out.println("최종요금은 7000원입니다.");
			}
		} else {
			if(day.equals("토") || day.equals("일")) {
				System.out.println("성인 요금입니다.(주말 할인 적용)");
				System.out.println("최종요금은 8000원입니다.");
			} else {
				System.out.println("성인 요금입니다.");
				System.out.println("최종요금은 10000원입니다.");
			}
		}
		*/
	}

}
