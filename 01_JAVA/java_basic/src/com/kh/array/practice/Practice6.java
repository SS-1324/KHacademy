package com.kh.array.practice;

import java.util.Scanner;

public class Practice6 {

	/*
	 	### 문제 설명
		크기가 N × M인 두 정수 행렬 A, B가 주어질 때, 두 행렬의 원소별 합을 구해 출력하세요.
		---
		### 입력 조건
		- 첫째 줄: 정수 `N`, `M` (1 ≤ N, M ≤ 100)
		- 다음 `N`줄: 행렬 **A**의 원소 `M`개
		- 다음 `N`줄: 행렬 **B**의 원소 `M`개
		- 각 원소는 **−100 이상 100 이하**의 정수
		---
		### 출력 조건
		- `N`줄에 걸쳐, 각 줄마다 `M`개의 정수를 **공백으로 구분**하여 **A + B**의 결과 행렬을 출력
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] arrA = new int[n][m];
		int[][] arrB = new int[n][m];
		
		for(int i=0; i<arrA.length; i++) { //행을 넘겨주는 역할
			for(int j=0; j<arrA[i].length; j++) { //arrA[i]행의 열을 넘겨주는 역할
				arrA[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<arrB.length; i++) { //행을 넘겨주는 역할
			for(int j=0; j<arrB[i].length; j++) { //arrB[i]행의 열을 넘겨주는 역할
				arrB[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<arrA.length; i++) { //행을 넘겨주는 역할
			for(int j=0; j<arrA[i].length; j++) { //arrA[i]행의 열을 넘겨주는 역할
				System.out.print(arrA[i][j] + arrB[i][j] + " ");
			}
			System.out.println();//행이 바뀔때마다 개행
		}
	}

}
