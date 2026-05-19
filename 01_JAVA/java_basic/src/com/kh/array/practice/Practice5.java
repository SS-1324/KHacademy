package com.kh.array.practice;

import java.util.Scanner;

public class Practice5 {

	/*
	 	### 입력 조건
		- 첫째 줄: N (1 ≤ N ≤ 100), M (1 ≤ M ≤ 100)
		- 다음 M줄: i j (1 ≤ i ≤ j ≤ N)
		---
		### 출력 조건
		- 작업 수행 후, 1번 바구니부터 N번 바구니에 쓰인 번호를 공백으로 구분하여 출력
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //바구니 수
		int m = sc.nextInt(); //반복작업 수
		int[] basket = new int[n];
		for(int i=0; i<basket.length; i++) {
			basket[i] = i + 1;
		}
		
		for(int k=0; k < m; k++) {
			int i = sc.nextInt() - 1; //i번째 바구니부터 -> index로 변경
			int j = sc.nextInt() - 1; //j번째 바구니까지 -> index로 변경
			
			//i번째 인덱스 값부터 j번째인덱스값까지 역순으로 정렬
			//뒤에값부터 순차적으로 앞에값에 넣자!
			//앞에값이 사라지기 전에 보관을 잠시 해야겠다.
			while(i < j) {
				int tmp = basket[i];
				basket[i] = basket[j];
				basket[j] = tmp;
				i++;
				j--;
			}
		}
		
	}

}
