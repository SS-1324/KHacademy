package com.kh.example.oop3;

public class Run {

	public static void main(String[] args) {
		Book b1 = new Book();
		Book b2 = new Book("자바는 즐거워", "KH", "최지원");
		Book b3 = new Book("자바는 즐거워2", "KH", "최지원", 10000, 0.2);

		b1.inform();
		b2.inform();
		b3.inform();
	}

}
