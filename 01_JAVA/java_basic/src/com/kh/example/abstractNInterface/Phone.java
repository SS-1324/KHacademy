package com.kh.example.abstractNInterface;

public interface Phone {
	/*public static final*/ 
	char[] NUMBERPAD = {'1','2','3','4','5','6','7','8','9','*','0','#'};

	//추상메서드 : 구현부가 없는 메서드
	/*public abstract*/
	String makeCall();
	String takeCall();
}
