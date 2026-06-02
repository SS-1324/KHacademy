package com.kh.example.api;

import java.util.StringTokenizer;

public class TokenController {

	TokenController() {
		super();
	}
	
	//띄어쓰기 제거 후 문자열 반환
	public static String afterToken(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		
		StringBuilder sb = new StringBuilder();
		while(st.hasMoreElements()) {
			sb.append(st.nextToken());
		}
		
		return sb.toString();
	}
	
	//첫글자만 대문자
	public static String firstCap(String input) {
		char first = input.toUpperCase().charAt(0);
		return first + input.substring(1);
	}
	
	//input안에 one가 몇개인지 반환
	public static int findChar(String input, char one) {
		int count = 0;
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == one) {
				count++;
			}
		}
		return count;
	}
}
