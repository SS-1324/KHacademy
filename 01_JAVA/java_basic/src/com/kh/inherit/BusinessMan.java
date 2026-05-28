package com.kh.inherit;

public class BusinessMan extends Man{
	private String company;
	private String position;
	
	public BusinessMan(String name, String company, String position) {
		//super(); 부모클래스의 메모리공간을 가르키는 키워드
		//생정자의 맷 첫번째 줄에 부모 생성자를 먼저 호출해 줘야한다.
		super(name);
		//생략시 부모의 (기본)생성자를 호출 -> 부모의 기본생성자가 없다면 무조건 명시적으로 써야함.
		
		this.company = company;
		this.position = position;
		System.out.println("BusinessMan의 생성자");
	}

	public String getCompany() {
		return company;
	}

	public String getPosition() {
		return position;
	}

	public void setCompany(String company) {
		this.company = company;
	}



	public void setPosition(String position) {
		this.position = position;
	}

	public void tellYourInfo() {
		System.out.println("my company is " + company);
		System.out.println("my position is " + position);
		super.tellYourName(); //super키워드를 통해 부모 메모리 접근 가능
	}
}
