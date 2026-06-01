package com.kh.example.poly2;

public class Book {
	private String title;
	private String author;
	private String publisher;
	
	Book() {
		super();
	}
	Book(String title, String author, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String toString() {
		String str = "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
		return str;
	}
	
}
