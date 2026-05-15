package com.kh.loop;

public class Practice2 {

	public static void main(String[] args) {
		// 1~100까지의 수 중에서 짝수의 합을 구해라.
		//[console]
		// 1~100까지 짝수의 합 : xxx

		int sum = 0;
		for(int i=1; i<101; i++) {
			if(i % 2 == 0) { //짝수
				sum += i;
			}
		}
		
		System.out.println("1~100까지 짝수의 합 : " + sum);
	}

}
