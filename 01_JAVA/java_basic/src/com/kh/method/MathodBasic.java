package com.kh.method;

public class MathodBasic {
	/*
	 	메서드란
	 	- class내부에 정의된 함수를 method라고 부름.
	 	- 특정 기능을 수행하는 코드블럭을 정의하고, 필요할 때 호출해서 재사용 가능.
	 	- 중복 코드 제거와 가독성, 유지보수성이 향상됨
	 	
	 	[기본구조]
	 	
	 	[접근제한자] [static] 반환형 메서드이름(매개변수){
	 		//함수에서 실행할 코드
	 		[return 값]; 
	 	}
	 */

	//main메서드 : 프로그램을 시작할 때 사용되는 메서드
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		//hiEverone(12);
		//hiEverone(20);
		{
			int num = 10;
			System.out.println(num);
		}
		
		System.out.println(num);
		System.out.println(adder(123, 222));
		System.out.println(adder(500, 2222222));
		System.out.println("프로그램 끝");
		System.out.println(square(3.15));
	}
	
	//매개변수 : 함수가 수행할 기능에 필요한 값들을 외부로부터 전달받기 위한 변수
	public static void hiEverone(int age) {
		System.out.println("좋은 아침입니다.");
		System.out.println("제 나이는 " + age + "입니다.");
	}
	
	//반환형 : 함수의 결과로 돌려줄 값의 타입, 반환값이 없을 시 void.
	public static int adder(int num1, int num2) {
		//System.out.printf("%d + %d = %d \n", num1, num2, (num1 + num2));
		return (num1 + num2);
	}
	
	public static double square(double num) {
		return num * num;
	}
}
