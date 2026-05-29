package com.kh.polymorphism;

public class CheeseCake extends Cake{
	public void milky() {
		System.out.println("치즈케이크 느끼하다");
	}
	
	//부모클래스의 Cake에도 void yummy()이 존재함
	//자식클래스에서 재정의(오버라이딩)
	public void yummy() {
		System.out.println("치즈케이크 냠냠");
	}
}
