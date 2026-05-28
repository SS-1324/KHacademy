package com.kh.inherit;

public class Desktop extends Product{
	private boolean allinOne; //일체형여부

	public Desktop() {
		//super(); 부모클래스의 기본생성자 호출
		//생략시에도 자동으로 기본생성자 호출
	}

	public Desktop(String pName, int price, String brand, boolean allinOne) {
		super(pName, price, brand);
		this.allinOne = allinOne;
	}

	public boolean isAllinOne() {
		return allinOne;
	}

	public void setAllinOne(boolean allinOne) {
		this.allinOne = allinOne;
	}
	
	
	public String inform() {
		return super.inform() + " / 올인원 : " + allinOne; 
	}
	
}
