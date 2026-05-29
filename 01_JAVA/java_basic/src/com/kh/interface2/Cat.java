package com.kh.interface2;

public class Cat extends Animal{
	//Animal 추상클래스를 상속한다고 선언하는 순간
	//추상클래스에 있는 모든 추상메서드는 오버라이딩 해야한다.
	
	@Override
	public void speak() {
		System.out.println("야옹~");
	}
	

}
