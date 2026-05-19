package com.kh.array.practice;

import java.util.Scanner;

public class Practice3 {

	/*
	 	### 입력 조건
		- 첫째 줄: N (1 ≤ N ≤ 100), M (1 ≤ M ≤ 100)
		- 다음 M줄: i j k (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ N)
		---
		### 출력 조건
		- 1번 바구니부터 N번 바구니까지 **각 바구니에 들어있는 공의 번호를 공백으로 구분하여 출력** (공이 없는 경우 0)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); //바구니의 수	
		int[] iArr = new int[n]; //배열 -> 바구니
		
		int m = sc.nextInt(); //작업수 -> 반복횟수
		for(int h=0; h<m; h++) {
			int i = sc.nextInt(); // i번째 바구니(배열)
			int j = sc.nextInt(); // j번째 바구니(배열)까지
			int k = sc.nextInt(); // k를 넣겠다.
			for(int idx = i-1; idx <= j-1; idx++) {
				iArr[idx] = k;
			}
		}
		
		for(int num : iArr) {
			System.out.print(num + " ");
		}

	}

}
