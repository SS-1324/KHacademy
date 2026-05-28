package com.kh.example.oop7;

public class Product {
	private String pName;
	private int price;
	private String brand;
	
	//생성자
	public Product() {
		super();
	}

	public Product(String pName, int price, String brand) {
		super();
		this.pName = pName;
		this.price = price;
		this.brand = brand;
	}

	//메서드
	public String getpName() {
		return pName;
	}

	public int getPrice() {
		return price;
	}

	public String getBrand() {
		return brand;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String inform() {
		return  "상품명 : "+pName+" / 가격 : "+price+" / 브랜드 : " + brand;
	}
	
}
