package com.kh.loop;

import java.util.Scanner;

public class For {
	/*
	 반복문
	 특정 코드를 여러번 반복해서 실행하고자 할 때 사용하는 문법.
	 
	 for, while, do-while
	 */

	public static void main(String[] args) {
		/*
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		*/
		
		//10번 반복 출력
		for(int i=0; i<10; i++) {
			System.out.println("안녕하세요.");
		}
		
		/* n번반복
			for(int i=0; i<n; i++) {
				반복할 코드
			}
		*/
		
		
		System.out.println("안녕하세요.".charAt(0));
		System.out.println("안녕하세요.".charAt(1));
		System.out.println("안녕하세요.".charAt(2));
		System.out.println("안녕하세요.".charAt(3));
		System.out.println("안녕하세요.".charAt(4));
		System.out.println("안녕하세요.".charAt(5));
		for(int i=0; i<6; i++) {
			System.out.println("안녕하세요.".charAt(i));
		}
		
		for(int i=11; i<=17; i+=2) {
			System.out.println(i);
		}
		
		//1~5까지의 숫자를 순차적으로 출력(1 2 3 4 5)
		for(int i=1; i<6; i++) {
			System.out.print(i + " ");
		}
		
		/*
		 	블록 scope -> {}
		 	자바의 지역변수
		 	- 특정 메서드나 블록({}) 내부에서 선언하고 관리하는 변수
		 	- 해당 블록이 종료되면 블록 내부의 변수도 사라짐.
		 */
		System.out.println();
		for(int i=6; i>0; i--) {
			int num = 10;
			System.out.print(num - i + " ");
		}
		
		//System.out.println(num); - 블록 내부에서 선언된 변수로 외부사용 불가
		
		//정수 n을 입력받아 1부터 n까지 1씩 증가시키면서 출력해라
		//[console]
		//정수입력 : n
		// 1 2 3 4 5 ... n
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력 : ");
		int n = sc.nextInt();
		
		for(int i = 1; i<=n; i++) {
			System.out.print(i + " ");
		}
	}

}
