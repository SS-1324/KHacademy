package com.kh.inherit;

public class Man {
	private String name;
	
	public Man(String name) {
		super(); //Object클래스의 생성자
		this.name = name;
		System.out.println("Man의 생성자");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void tellYourName() {
		System.out.println("my name is " + name);
	}
	
	

}
