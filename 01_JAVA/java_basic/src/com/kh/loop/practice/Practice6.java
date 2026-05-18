package com.kh.loop.practice;

import java.util.Scanner;

public class Practice6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<10; i++) {
			System.out.print("숫자입력 : ");
			int num = sc.nextInt();
			
			if(num % 2 != 0) //홀수인경우
				continue;
				
			System.out.printf("짝수 %d의 제곱은 %d입니다. \n", num, num*num);
		}
		
		sc.close();

	}

}
