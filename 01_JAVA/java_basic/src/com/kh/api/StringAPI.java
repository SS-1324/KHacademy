package com.kh.api;

import java.util.StringTokenizer;

public class StringAPI {
	public void method1() {
		//1. 생성자를 통한 문자열 생성
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		System.out.println(str1);
		System.out.println(str2.toString());
		//String클래스에서 toString메서드를 오버라이딩 하고 있다.
		//그래서 참조변수 출력시 Object의 toString이 아닌 문자열반환하는 String의 toString이 호출됨.
	
		System.out.println(str1 == str2);
		//String객체의 주소값을 비교하기 때문에 false가 나옴
		
		System.out.println(str1.equals(str2));
		//String클래스에서 equals메서드도 이미 오버라이딩 되어있음(주소값 비교가 아니라 문자열 비교)
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		//String클래스에서 hashCode메서드도 이미 오버라이딩 되어있음(주소값이 아닌 문자열을 가지고 해시값을 만들고 있음)
		
		//메모리값 기반으로 해시값을 보고싶다.
		//System.identityHashCode(참조변수)
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		
		
		System.out.println("-----------------------------------------------------");
		
		//2. 문자열을 리터럴값으로 생성
		String str3 = "hello";
		String str4 = "hello";
		//문자열 리터럴 사용시 상수풀영역에 문자열을 생성.
		//String pool : 동일한 문자열을 중복해서 가지지 않는다.
		
		str3 = str3 + " world";
		str4 = "bye";
		//String 리터럴의 특성상 참조변수에 값을 변경하는 순간 기존의 문자열 메모리에 값을 변경하는게 아니라
		//상수이기 때문에 새로운 값을 String pool에 만들고 참조하도록 한다(불변성)
	}
	
	public void method2() {
		String str1 = "Hello World";
		
		//문자열.charAt(index) : char
		//문자열에서 전달받은 index위치의 문자를 추출
		char ch = str1.charAt(6);
		System.out.println("ch : " + ch);
		
		
		//문자열.contains(키워드) : boolean
		//문자열에 전달된 키워드가 포함되어 있는지 여부 확인
		System.out.println("str1에 ello 키워드 여부 : " + str1.contains("ello"));
		System.out.println("str1에 bye 키워드 여부 : " + str1.contains("bye"));
		
		//문자열.substring(시작위치, [끝위치])
		//문자열을 시작위치부터 끝위치 -1까지 추출해서 반환
		System.out.println(str1.substring(6));
		System.out.println(str1.substring(6, 9));
		
		//문자열.replace(대상문자, 변경할문자) : String
		//문자열에서 대상문자를 찾아 변경할문자로 치환한 후 변환된 문자열을 반환
		System.out.println(str1.replace('l', 'c'));
		
		System.out.println("모든문자열 대문자 : " + str1.toUpperCase());
		System.out.println("모든문자열 소문자 : " + str1.toLowerCase());
		
		//문자열.trim();
		//문자열의 앞뒤공백을 제거
		String str5 = "     Hello     wolrd    ";
		System.out.println(str5.trim());
		
		//문자열.toCharArray()
		//문자열을 문자배열로 변환
		char[] arr = str1.toCharArray();
		for(char c : arr) {
			System.out.println(c);
		}
	}
	
	public void method3() {
		String str = "Java,Oracle,sql,html,css,js,jsp,Spring";
		
		//구분자를 기준으로 문자열을 분리시키는 법
		
		//방법1. 분리된 문자열을 String[]에 차곡차곡 담아야 할때
		//문자열.split(구분자) : String[]
		String[] arr = str.split(",");
		for(String st : arr) {
			System.out.print(st + " ");
		}
		
		System.out.println();
		
		//분리된 문자열 배열을 다시 String으로 연결하는 방법
		//String.join(구분자, 배열)
		String str2 = String.join(",", arr);
		System.out.println(str2);
		
		//방법2 분리된 문자열을 각각 토큰으로 관리하는 방법
		//문자열 토큰화 : StringTokenizer
		StringTokenizer stn = new StringTokenizer(str, ",");
		System.out.println(stn.countTokens()); //토큰의 남은갯수
		
		System.out.println(stn.nextToken());
		System.out.println(stn.countTokens()); 
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
//		System.out.println(stn.nextToken()); 더이상 토큰이 남아있지 않아서 다음토큰 호출시 예외발생
	
		stn = new StringTokenizer(str, ",");
		int size = stn.countTokens();
		for(int i=0; i<size; i++) {
			System.out.println(stn.nextToken());
		}
		
		stn = new StringTokenizer(str, ",");
		System.out.println(stn.hasMoreElements());//남은 토큰이 있니?
		while(stn.hasMoreElements()) {
			System.out.println(stn.nextToken());
		}
	}
}
