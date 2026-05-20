package com.kh.array.practice;

import java.util.Scanner;

public class Practice7 {
	/*
	 	### 문제 설명
		`N × M` 크기의 2차원 배열이 주어질 때, 배열에 포함된 **가장 큰 값**과 그 값이 위치한 **행 번호와 열 번호**를 출력하세요.
		- **행 번호와 열 번호는 1부터 시작**합니다.
		---
		### 입력 조건
		- 첫째 줄: 정수 `N`, `M` (1 ≤ N, M ≤ 100)
		- 다음 `N`줄: 각 줄에 `M`개의 정수(−1000 이상 1000 이하)가 주어짐
		---
		### 출력 조건
		- 첫째 줄: 배열의 최댓값
		- 둘째 줄: 최댓값이 위치한 행 번호와 열 번호를 공백으로 구분해 출력
		- **최댓값이 여러 개일 경우, 가장 앞에 나오는 위치**(행 우선, 그 다음 열 우선)를 출력
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] arr = new int[n][m];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int max = Integer.MIN_VALUE; //가장작은값 or 첫번째 값
		int x=0, y=0;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(max < arr[i][j]) {
					max = arr[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		System.out.println(max);
		System.out.printf("%d %d", x+1, y+1);
	}

}
