package com.kh.loop.practice;

public class Practice7 {

	public static void main(String[] args) {
		//2단~9단
		for(int j=2; j<=9; j++) {
			for(int i=1; i<=9; i++) {
				System.out.printf("%d * %d = %d\n",j , i, j*i);
			}
		}
	}

}
