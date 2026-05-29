package com.kh.interface2;

//추상클레스
public abstract class Animal {
	
	public void breathe() {
		System.out.println("숨을 쉰다~");
	}
	
	//추상메서드
	public abstract void speak();
}
