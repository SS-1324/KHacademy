package com.kh.loop.practice;

public class Practice4 {

	public static void main(String[] args) {
		for(int i=1; i<=100; i++) {
			//7의 배수가 아니거나 짝수인 숫자
			if(i % 7 != 0 || i % 2 == 0) {
				continue;
			} 
			
			// 7의 배수이면서 홀수인 숫자
			System.out.print(i + " ");
		}

	}

}
