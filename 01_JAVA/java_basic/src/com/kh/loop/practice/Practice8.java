package com.kh.loop.practice;

import java.util.Scanner;

public class Practice8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++) { //i=0, 1, 2, 3...n
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
