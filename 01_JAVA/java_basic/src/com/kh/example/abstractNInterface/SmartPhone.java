package com.kh.example.abstractNInterface;

public abstract class SmartPhone {
	private String maker;
	
	public SmartPhone() {}
	
	//메서드
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}


	//추상메서드
	public abstract String printInformation();
}
