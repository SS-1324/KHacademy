package com.kh.review.basic;

public class Basic {

	public static void main(String[] args) {
		// ============================================
		// 1. 변수 - 값을 담는 상자
		// 종류(타입)에 따라서 담을 수 있는 값이 다르다.
		
		// [표현식]
		// 타입 변수명;  
		// 변수명 = 값;  타입 변수명 = 값;
		
		int age = 20; 			  // 정수(기본타입)
		double hegight = 180.5;   // 소수(기본타입) float weight = 90.7f;// 소수(소수점 6번째 까지만 표현하는 소수) 
		boolean isStudent = true; // 참/거짓 (true or false)
		String name = "김철수"; 	  // 글자(문자열)
		char gender = '남';		  // 글자(문자한개)
		
		System.out.println("======== 변수 ===========");
		System.out.println("이름 : " + name);
		System.out.println("키 : " + hegight);
		System.out.println("학생여부 : " + isStudent);
		System.out.println("나이 : " + age);
		System.out.println("성별 : " + gender);
		
		age = age + 10;
		name = "김민수";
		System.out.println("저는 " + age + "살 이고 " + name + "입니다.");

		// ====================================================
		// 2. 조건문 - 만약 ~~라면 이렇게 해
		// 비교 기호: >, <, >=, <=, ==, !=
		
		System.out.println("===============조건문==================");
		int score = 85;
		
		//if ~ else if ~ else : 조건에 맞는 코드만 실행
		if(score >= 90) {
			System.out.println("학점 : A");
		} else if(score >= 80){
			System.out.println("학점 : B");
		} else if(score >= 70){
			System.out.println("학점 : C");
		} else {
			System.out.println("학점 : F");
		}
		
		
		age = 19;
		isStudent = false;
		// 두 조건을 함께 걸기 &&(그리고) ||(또는)
		//나이가 20살이상이면서, 성인이면서 학생입니다.를 출력 
		if(age >= 20 && isStudent) {
			System.out.println("성인이면서 학생입니다.");
		} else if(isStudent) {
			System.out.println("학생입니다.");
		}
		
		System.out.println();
		gender = '여';
		
		// switch : 전달한 변수와 딱맞는 case를 찾아서 실행
		//          딱맞는 case부터 아래로 모든 코드를 실행함.
		//         break; -> switch 탈출문
		switch(gender) {
			case '남': System.out.println("남자입니다."); break;
			case '여': System.out.println("여자입니다."); break;
			default: System.out.println("성별... 모르겠음...");
		}
		
	}

}
