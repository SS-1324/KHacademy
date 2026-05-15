package com.kh.loop;

import java.util.Scanner;

public class Practice5 {
	//사용자가 0을 입력할 때까지 랜덤값(1~100)을 하나씩 생성해서
	//모두 더한 값을 출력
	
	//[console]
	//숫자입력(0입력시 종료): n
	//랜덤값누적 : ~~
	//...
	//숫자입력(0입력시 종료): 0
	//랜덤값누적 : ~~ 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int num = 0;
		do {
			System.out.print("숫자입력(0입력시 종료):");
			num = sc.nextInt();
			sum += ((int)(Math.random() * 100) + 1);
			System.out.println("랜덤값누적 : " + sum);
		} while(num != 0);
	}
}
