package com.kh.example.oop3;

public class Book {
	String title;
	String publisher;
	String author;
	int price;
	double discountRate;
	
	public Book() {
		super();
	}

	public Book(String title, String publisher, String author) {
		this(title, publisher, author, 0, 0);
	}

	public Book(String title, String publisher, String author, int price, double discountRate) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.price = price;
		this.discountRate = discountRate;
	}
	
	void inform() {
		System.out.printf("%s, %s, %s, %d, %f \n", title, publisher, author, price, discountRate);
	}
}
