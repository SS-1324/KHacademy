package com.kh.operator;

import java.util.Scanner;
//불러올 객체에 커서 가져다놓은 뒤
// controll + shift + o

public class Operator1 {

	//1. ! : 논리부정 연산자 -> 논리값을 반대로 변경함.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("true의 부정 : " + !(true));
		System.out.println("false의 부정 : " + !(false));
		
		System.out.print("값1 : ");
		int num1 = sc.nextInt();
		
		System.out.print("값2 : ");
		int num2 = sc.nextInt();
		
		System.out.println("값1이 값2보다 크지않다. : " + !(num1 > num2));
	}

}
