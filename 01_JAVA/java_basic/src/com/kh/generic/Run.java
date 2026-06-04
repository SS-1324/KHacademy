package com.kh.generic;

public class Run {

	/*
	 	제네릭
	 	제네릭을 사용하지않고 Obejct같은 넓은 범위를 수용할 수 있는 타입을 사용시 -> 형변환필요, 런타임오류...
	 	클래스나 메서드가 사용할 데이터타입을 컴파일시점에 지정할 수 있도록 지원하는 문법 -> 매개타입
	 	"필드, 메서드의 타입을 객체 생성시 전달받아 사용한다"
	 */
	public static void main(String[] args) {
		Box aBox = new Box<>(); //제네릭 사용 후 타입을 전달하지 않으면 기본적으로 Object
		aBox.setValue("10"); 
		int num = Integer.parseInt((String)aBox.getValue());
		System.out.println(num);
		
		Box<Integer> iBox = new Box<>();
		//iBox.setValue("10"); 매개타입을 Interger로 전달했기 때문에 String전달 불가
		iBox.setValue(100);
		int num2 = iBox.getValue();	
	}

}
