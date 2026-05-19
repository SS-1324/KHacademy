package com.kh.array.practice;

import java.util.Scanner;

public class Practice2 {

	/*
	 	### 입력 조건
		- 첫째 줄: 정수의 개수 N (1 ≤ N ≤ 1,000,000)
		- 둘째 줄: N개의 정수가 공백으로 구분되어 주어집니다. 각 정수는 −1,000,000 이상, 1,000,000 이하
		---
		### 출력 조건
		- 첫째 줄에 **최솟값과 최댓값을 공백으로 구분하여 출력**합니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();	
		int[] iArr = new int[n];

		//입력
		for(int i=0; i<iArr.length; i++) {
			iArr[i] = sc.nextInt();
		}
		
		int min = iArr[0], max = iArr[0];

		for(int num : iArr) {
			min = (num < min) ? num : min;
			max = (num > max) ? num : max;
		}
		
		System.out.println(min + " " + max);
	}

}
