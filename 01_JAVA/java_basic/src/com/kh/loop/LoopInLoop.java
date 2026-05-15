package com.kh.loop;

public class LoopInLoop {

	public static void main(String[] args) {
		int dan = 2;
		
		/*
		System.out.printf("%d * %d = %d \n", dan, 1, (dan*1));
		System.out.printf("%d * %d = %d \n", dan, 2, (dan*2));
		System.out.printf("%d * %d = %d \n", dan, 3, (dan*3));
		System.out.printf("%d * %d = %d \n", dan, 4, (dan*4));
		System.out.printf("%d * %d = %d \n", dan, 5, (dan*5));
		System.out.printf("%d * %d = %d \n", dan, 6, (dan*6));
		System.out.printf("%d * %d = %d \n", dan, 7, (dan*7));
		System.out.printf("%d * %d = %d \n", dan, 8, (dan*8));
		System.out.printf("%d * %d = %d \n", dan, 9, (dan*9));
		*/
		
		//규칙성을 가지고 반복하는 코드는 반복문을 활용할 수 있다.
		/*
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 2, i, (2*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 3, i, (3*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 4, i, (4*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 5, i, (5*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 6, i, (6*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 7, i, (7*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 8, i, (8*i));
		}
		
		for(int i = 1; i<=9; i++) {
			System.out.printf("%d * %d = %d \n", 9, i, (9*i));
		}
		*/
		//구구단
		for(int j = 2; j<=9; j++) {
			for(int i = 1; i<=9; i++) {
				System.out.printf("%d * %d = %d \n", j, i, (j*i));
			}
		}
	}

}
