package com.kh.object.overload;

public class Man {
	//필드
	String name;
	String number; //주민등록번호
	
	//초기화값 -> 2개, 전달값 1개
	public Man(String name) {
		this(name, "입력안됨");
		//this()를 통해서 해당 객체의 생성자를 생성자에서 호출 할 수 있다.
	}

	//초기화값 -> 2개
	public Man(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	//오버로딩
	//이름이 동일한 메서드를 매개변수를 달리하여 정의할 수 있다.
	public void showMyInfo() {
		System.out.println("저의 이름은 " + name + "입니다.");
	}
	
	public void showMyInfo(String gender) {
		System.out.println("저의 이름은 " + name + "입니다. 저는 " + gender + "입니다.");
	}
	
	
	//생성자
	//(alt + shift + s) -> o
		
	
	//메서드
	

}
