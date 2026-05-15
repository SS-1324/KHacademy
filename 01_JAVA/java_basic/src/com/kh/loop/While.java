package com.kh.loop;

import java.util.Scanner;

public class While {
	/*
	 while문
	 
	 while(조건식){
	 	반복할 코드
	 	+[탈출이 가능한 구조]
	 }
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<10; i++) {
			System.out.println("안녕하세요.");
		}
		
		int i=0; //조건식에 사용할 값이 있어야함.
		while(i<10) {
			System.out.println("안녕하세요.");
			i++;//조건에 영향을 주는 증감
		}
		
		//사용자가 0을 입력할 때까지 반복해서 입력받아 그대로 출력
		/*
		int n = 1;
		while(n != 0) {
			n = sc.nextInt();
			System.out.println(n);
		}
		
		for(int n = 1;n != 0;n = sc.nextInt()) {
			System.out.println(n);
		}
		*/
		
		
		//랜덤값(1~100) 하나 생성
		// 1~ 랜덤값까지의 합을 while로 작성
		// [console]
		// 랜덤값 : n
		// 1~n까지의 합 : xxx
		int random = (int)(Math.random() * 100) + 1;
		System.out.println("랜덤값 : " + random);
		
		int sum = 0;
		
		/*
			int j = 1;
			while(j <= random) {
				sum+=j;
				j++;
			}
		*/
		for(int j = 1;j <= random; j++) {
			sum+=j;
		}
		
		System.out.println("1~n까지의 합 : " + sum);
		
		int num = 0;
		while(num != 3) {
			System.out.println("서비스 번호를 입력하세요 : ");
			System.out.println("1. 추가");
			System.out.println("2. 삭제");
			System.out.println("3. 종료");
			
			System.out.print("입력 : ");
			num = sc.nextInt();
			
			switch(num) {
			case 1: System.out.println("추가되었습니다."); break;
			case 2: System.out.println("삭제되었습니다."); break;
			case 3: System.out.println("종료되었습니다."); break;
			default: System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
