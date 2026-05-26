package com.kh.option;

/*
 	정보은닉 : 객체내의 정보를 의도와다르게 변경하는 것을 막기위해
 	        직접접근을 막고 메서드를 통한 접근방식을 사용하는 것.
 	
 	직접접근을 막는 방법 = 접근제한자를 사용
 	접근제한자 : 클래스, 필드, 메서드등 구성요소의 접근 범위를 제한하는 키워드
 	
 	public > protected > default > private
 	
 	public : 어디서든 접근 가능
 	protected : 같은 패키지 + 상속관계에 있는 경우 
 	default : 같은 패키지 
 	private : 오직 클래스내에서만 접근가능
 */
public class Book {
	//필드(private) -> 정보은닉
	private String title;
	private String publisher;
	private String author;
	private int price;
	private double discountRate;
	
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if(price < 0) { // 0보다 작은 가격은 존재x
			this.price = 0;
			return;
		}
		this.price = price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		if(discountRate < 0 || 1 < discountRate) { // 0보다 작은 가격은 존재x
			this.discountRate = 0;
			return;
		}
		this.discountRate = discountRate;
	}

	void inform() {
		System.out.printf("%s, %s, %s, %d, %f \n", title, publisher, author, price, discountRate);
	}
}
