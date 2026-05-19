package com.kh.array.practice;

import java.util.Scanner;

public class Practice1 {

	/*
		### 입력 조건
		- 첫째 줄: 정수 N과 X (1 ≤ N, X ≤ 10,000)
		- 둘째 줄: 수열 A를 이루는 정수 N개 (각 원소는 1 이상 10,000 이하)		
		---		
		### 출력 조건
		- A에서 X보다 작은 수들을 **입력된 순서대로 공백으로 구분해 출력**합니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(), x = sc.nextInt();
		
		int[] iArr = new int[n];

		//입력
		for(int i=0; i<iArr.length; i++) {
			iArr[i] = sc.nextInt();
		}
		
		//출력
		for(int i=0; i<iArr.length; i++) {
			if(iArr[i] < x) {
				System.out.print(iArr[i] + " ");
			}
		}
		
	}

}
