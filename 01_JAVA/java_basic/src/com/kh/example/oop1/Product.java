package com.kh.example.oop1;

public class Product {
	//필드
	String pName;
	int price;
	String brand;
	
	//생성자
	/* 기본생성자는 생성자 작성 안할시 자동 생성됨.
	public Product() {
		super();
	}
	*/
	
	void information() {
		System.out.printf("상품 : %s, 가격: %d, 브랜드 : %s \n", pName, price, brand);
	}
	
	
}
