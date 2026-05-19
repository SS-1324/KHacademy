package com.kh.array.practice;

import java.util.Scanner;

public class Practice4 {

	/*
	 	### 입력 조건
		- 28개의 줄에 서로 다른 출석번호 (1~30)이 주어집니다.
		---
		### 출력 조건
		- 과제를 제출하지 않은 학생 2명의 출석번호를 **오름차순으로 한 줄에 하나씩 출력**합니다
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean[] submitStd = new boolean[30]; //1~30번(0~29) 제출확인배열
		
		//28명제출
		for(int i=0; i<28; i++) {
			int num = sc.nextInt(); //5 -> 4, 1 -> 0
			submitStd[num - 1] = true;
		}
		
		for(int i=0; i<submitStd.length; i++) {
			if(!submitStd[i]) {
				System.out.println(i + 1);
			}
		}
	}

}
