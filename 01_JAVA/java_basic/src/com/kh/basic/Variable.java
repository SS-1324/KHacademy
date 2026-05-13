package com.kh.basic;

// 한줄주석
/*
 여러줄 주석 
*/

public class Variable {
	/*
	 * 변수 : 값을 기록하고 사용하기위한 메모리공간
	 * 
	 * [표현식]
	 * 자료형 변수이름;
	 */
	
	public static void main(String[] args) {
		//정수형 변수 num을 선언
		int num;
		
		//정수형 변수 num에 10이라는 값을 대입
		num = 10;
		System.out.println(num);
		//syso + controll + space => 엔터 자동완성
		
		//참과 거짓을 표현하는 자료형으로 isTrue라는 이름의 변수를 선언
		boolean isTrue = false; //선언과 동시에 false라는 값을 대입
		isTrue = true;
		System.out.println(isTrue);
		
		//실수형(소수점 6자리까지 출력)으로 num2라는 이름의 변수를 선언
		float num2;
		num2 = 3.1412344445456161f;
		System.out.println(num2);
		System.out.printf("%.6f", num2);
		System.out.println();
		
		//정수 자료형
		int in = 10; // 기본값 4바이트
		byte by = 10; // 1바이트
		short sh = 10; // 2바이트
		long lo = 10; // 8바이트
		
		//실수 자료형
		float fl = 4.24f; // 4바이트
		double dou = 4.24; // 8바이트
		System.out.println(fl);
		System.out.println(dou);
		
		//문자 자료형
		char ch1 = '최';
		char ch2 = '지';
		char ch3 = '원';
		//기본적으로 char는 연산시 int로 형변환된다.
		System.out.println(ch1 + ch2 + ch3); 
		System.out.println(ch1);
		
		//논리자료형
		boolean b1 = 10 > 0;
		boolean b2 = 20 == 10;
		System.out.println(b1);
		System.out.println(b2);
		
		/*
		 * 원시타입 : 가장 기본적인 데이터타입, 값 자체를 저장하며 추가적인 속성이 없다.
		 * 
		 * 문자열
		 * String
		 * 문자열은 할당 메모리크기가 가변적이므로 원시타입 아닌 객체타입으로 변수를 만든다.
		 * 
		 * 원래 객체를 만드는 방식
		 * String 변수명 = new String(초기값);
		 * 
		 * 문자열 특별히 자주사용하기 때문에 원시타입과 동일하게 생성, 사용이 가능함
		 */
		String str1 = "안녕하세요. 김아무개입니다.";
		System.out.println(str1);
		
		//문자열에는 이스케이프 시퀀스가 존재함
		//문자열내에서 탭, 백슬러시, 작은따옴표등을 사용하기위한 방식
		
		// \t : 탭
		System.out.println("이름\t나이\t주소");
		
		// \\ : 백슬러시
		System.out.println("이름\\나이\\주소");
		
		// \" : 쌍따옴표
		System.out.println("이름\"나이\"주소");
		
		// \n : 개행
		System.out.println("이름\n나이\n주소");
		
		/*
		 * 상수
		 * 수학 -> 변하지않는 수
		 * 프로그래밍 -> 한번만 쓸 수 있는 메모리
		 * 
		 * final 자료형 변수명;
		 * 
		 * 상수의 변수명은 모두 대문자로 짓는 것이 일반적이다.
		 */
		
		final int MY_AGE;
		MY_AGE = 100;
		//MY_AGE = 111;
		System.out.println(MY_AGE);
		
		/*
		  프로그래밍에서 이름 짓는 방식
		  
		  1. 카멜케이스 : 단어를 나열할 때 두번째 단어부터는 단어의 첫글자를 대문자로 시작.
		  ex) userName, totalCount
		  -> 자바에서 모든 함수명, 변수명
		  
		  2. 파스칼케이스 : 카멜케이스에서 첫글자까지 대문자로 작성
		  ex) UserName, TotalCount
		  -> 자바에서 클래스명에 사용
		  
		  3. 스네이크케이스 : 문자와 문자사이를 _를 통해서 이어주는 것
		  ex) user_name, total_count
		  -> 파이썬, 자바스크립트에서 많이사용
		  
		  4. 대문자스네이크케이스
		  ex) USER_NAME
		  -> 대부분언어에서 상수명
		  
		  5. 케밥케이스 : 단어는 소문자로 작성하되 문자와 문자사이를 하이픈으로 구성
		  ex) user-name, total-count
		  -> html에서 속성, url, 설정파일
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
}
