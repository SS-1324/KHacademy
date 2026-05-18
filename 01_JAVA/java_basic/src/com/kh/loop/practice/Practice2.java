package com.kh.loop.practice;

import java.util.Scanner;

public class Practice2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int totalPrice = sc.nextInt(); //영수증 총 금액
		int n = sc.nextInt(); //구매한 물건의 갯수
		
		int sum = 0;
		for(int i=0; i<n; i++) { //n번 반복
			int a = sc.nextInt(); //물건의 가격
			int b = sc.nextInt(); //문건의 갯수
			
			sum += (a * b);
		}
		
		/*
		if(sum == totalPrice) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		*/
		System.out.println(sum == totalPrice ? "Yes" : "No");
		
		sc.close();
	}

}
