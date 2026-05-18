package com.kh.array;

import java.util.Scanner;

public class ArrayCopy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] origin = {1,2,3,4,5};
		
		System.out.println("======원본출력======");
		for(int n : origin) {
			System.out.print(n + " ");
		}
		
		System.out.println();
		
		//주소값 복사 -> origin을 단순대입해서 copy배열 생성
		int[] copy = origin;
		System.out.println("======복사본출력======");
		for(int n : copy) {
			System.out.print(n + " ");
		}
		
		System.out.println();
		
		copy[2] = 99;
		System.out.println("복사본 값 변경");
		
		System.out.println("======원본 출력 2======");
		for(int n : origin) {
			System.out.print(n + " ");
		}
		
		System.out.println();
		
		//copy배열의 값을 수정해도 원본의 값이 변경됨.
		//왜? origin과 copy는 참조변수이고, 같은 메모리를 참고하고 있기 때문에
		//-> 참조변수의 주소값이 동일
		
		//똑같은 배열을 따로 하나 만들고 싶을 때.
		/*
		 	1. for문 활용
		 	새로운 배열을 만들어서 원본배열의 값을 하나하나 복사함.
		 */
		int[] origin2 = {1,2,3,4,5};
		int[] copy2 = new int[origin.length];
		
		for(int i=0; i<copy2.length; i++) {
			copy2[i] = origin2[i];
		}
		
		copy2[2] = 99;
		
		System.out.println("======원본 출력 3======");
		for(int n : origin2) {
			System.out.print(n + " ");
		}
		System.out.println();
		System.out.println("======복사본 출력 3======");
		for(int n : copy2) {
			System.out.print(n + " ");
		}
		
		/*
		 	clone()
		 	java에서 제공하는 메서드(기능)
		 	배열의 모든 요소를 새로운 배열로 복사
		 */
		
		int[] origin3 = {1,2,3,4,5};
		int[] copy3 = origin.clone(); // origin과 동일한 새로운 메모리를 만들어줌.
	}
}
