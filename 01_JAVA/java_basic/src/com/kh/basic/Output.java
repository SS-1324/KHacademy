package com.kh.basic;

public class Output {

	public static void main(String[] args) {
		//코드를 사용하는 영역
		
		//출력문
		//print() -> 기본적인 출력문, ()안에 있는 값을 화며에 출력함.
		System.out.print("안녕하세요.\n");
		System.out.print("안녕하세요.\n");
		
		//println() -> 개행문자를 포함한 출력문
		System.out.println("안녕하세요. 최지원입니다.");
		System.out.println("안녕하세요. 최지원입니다.");
		
		String name = "최지원";
		int age = 17;
		String gender = "남자";
		
		//저는 ~~이고 ~~살, ~~입니다.
		System.out.println("저는 " + name + "이고 " + age + "살, " + gender + "입니다.");
	
		//printf() -> 포멧을 맞춰 출력을 하고 싶을 때
		System.out.printf("저는 %s이고 %.2f살, %s입니다.\n", name, (float)age, gender);
		/*
		 포멧안에서 사용하는 키워드
		 %s : 문자열
		 %c : 문자
		 %d : 정수
		 %f : 실수 -> 소숫점아래 자리수를 정할 수 있다.(%.5f -> 소숫점 아래 5자리 표시)
		*/
	}

}
