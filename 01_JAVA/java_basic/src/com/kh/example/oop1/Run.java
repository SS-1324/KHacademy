package com.kh.example.oop1;

public class Run {

	public static void main(String[] args) {
		Product p1 = new Product();
		p1.information();
		
		p1.pName = "스탠바이미";
		p1.price = 1000000;
		p1.brand = "LG";
		
		p1.information();

	}

}
