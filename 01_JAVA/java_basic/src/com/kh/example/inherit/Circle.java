package com.kh.example.inherit;

public class Circle extends Point{
	private int radius;

	Circle() {
		super();
	}

	Circle(int x, int y, int radius) {
		super(x,y); //Point(int x, int y) 생성자 호출
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String toString() {
		return super.toString() + ", " + this.radius;
	}
}
