package com.kh.option;

public class Counter {
	//클래스변수 -> 프로그램 시작과 동시에 static메모리에 별로도 할당, 프로그램 종료시 반환
	static int count = 0;
	
	//인스턴스변수 -> heap메모리에 저장되는 변수
	private String name;

	public Counter(String name) {
		super();
		this.name = name;
		count++; //객체 생성시마다 1증가
	}
	
	public void printCount() {
		System.out.println(name + " : " + count);
	}
	
	public static void showCount() {
		System.out.println("count : " + count);
	}

}
