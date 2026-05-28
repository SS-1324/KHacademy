package com.kh.example.inherit;

public class Point {
	private int x;
	private int y;
	
	Point() {
		super();
	}
	Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//객체의 필드정보를 반환하는 함수
	public String toString() {
		return this.x + ", " + this.y;
	}
}
