package com.kh.interface1;

public class Cat implements Animal{
	//Animal인터페이스를 구현한다고 선언하는 순간
	//인터페이스에 있는 모든 추상메서드는 오버라이딩 해야한다.
	@Override
	public void speak() {
		System.out.println("양옹");
	}

	@Override
	public void move() {
		System.out.println("고양이가 살금살금 기어갑니다.");
	}

}
