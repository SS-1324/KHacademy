package com.kh.objectarray;

public class Book {
	//필드
	private String title;
	private String genre;
	private String author;
	private int maxPage; //페이지수
	
	//생성자
	public Book() {
		super();
	}

	public Book(String title, String genre, String author, int maxPage) {
		super();
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.maxPage = maxPage;
	}

	//메서드
	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getAuthor() {
		return author;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	
	
	public String toString() {
		return "Book [" + title + ", " + genre + ", " + author + ", " + maxPage + "]";
	}
}
