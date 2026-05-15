package com.kh.loop;

public class Practice3 {

	public static void main(String[] args) {
		/*
		 Math.random();
		 java.lang.Math 클래스에서 기본적으로 제공하는 함수
		 
		 Math.random() -> 0.0~0.9999999 사이의 랜덤값 반환
		 최소값 ~ 최대값 랜덤 수 : (int)(Math.random() * (최대값 + 1 - 최소값)) + 최소값;
		*/
		
		
		//random(1~100)한 숫자 n을 생성한 다음 1부터 n까지의 합을 출력해라
		//[console]
		// random값 : n
		// 1~n까지의 합 : xxx
		int n = (int)(Math.random() * 100) + 1;
		System.out.println("random값 : " + n);
		
		int sum = 0;
		for(int i=1; i<=n; i++) {
			sum += i;
		}
		System.out.println("1~n까지의 합 : " + sum);

	}

}
