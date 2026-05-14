package com.kh.loop;

import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 커피주문 키오스크
		 
		 [출력]
		 아메리카노(1100원)를 몇잔 구매하시겠습니까? : n
		 
		 1잔 -> 1100원
		 2잔 -> 2200원
		 3잔 -> 3300원
		 ...
		 n잔 -> nnn원
		 결제하실 금액 xxxx원입니다.
		 */
		/*
		System.out.print("아메리카노(1100원)를 몇잔 구매하시겠습니까? :");
		int count = sc.nextInt();
		int sum = 0;
		
		for(int i=1; i<=count; i++) {
			sum += 1100;
			System.out.printf("%d잔 -> %d원\n", i, i * 1100);
		}
		
		System.out.println(" 결제하실 금액 " + sum + "원입니다.");
		*/
		
		
		//1~100까지의 합을 구해라.
		int sum = 0;
		for(int i=1; i<=100; i++) { // 1~100
			sum+=i;
		}
		System.out.println("1~100까지의 합 : " + sum);
		
		 
	}
}
