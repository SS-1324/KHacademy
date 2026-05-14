package com.kh.control.practice;

import java.util.Scanner;

public class Practice2 {

	public static void main(String[] args) {
		/*
		 1부터 6까지 눈이 있는 세 개의 주사위를 던졌을 때, 
		 아래 규칙에 따라 상금을 계산하는 프로그램을 작성하세요.
		 
		 ### 상금 계산 규칙

		| 경우 | 구체 조건 | 상금 계산식 |
		| 1 | 세 개 모두 같을 때 | 10,000원 + (같은 눈) × 1,000원 |
		| 2 | 같은 눈이 두 개만 같을 때 | 1,000원 + (같은 눈) × 100원 |
		| 3 | 모두 다른 눈일 때 | (최댓값) × 100원 |
		 */
		Scanner sc = new Scanner(System.in);
		
		System.out.println("입력 : ");
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		int n3 = sc.nextInt();
		int reward = 0;
		
		if(n1 == n2 && n2 == n3) { //3개가 같을 때
			reward = 10000 + n1 * 1000;
		} else if(n1 == n2 || n1 == n3 ) { //2개가 같을 때
			 //식에 같은 눈이 포함되는데 3개의 조건을
			// 한번에하면 어떤 눈이 같은지 알 수 없음.
			reward = 1000 + n1 * 100;
		} else if(n2 == n3) { //2개가 같을 때
			reward = 1000 + n2 * 100;
		} else {
			int max = n1 > n2 ? n1 : n2; // n1과 n2 중 더 큰 값 max 저장
			max = max > n3 ? max : n3; // max와 n3 중 더 큰 값 max 저장
			reward = max * 100;
		}
		
		System.out.println("출력 : \n" + reward);
	}

}
