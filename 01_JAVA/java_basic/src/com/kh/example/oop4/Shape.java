package com.kh.example.oop4;

public class Shape {
	//필드
	private int type; //3 = 삼각형, 4 = 사각형
	private double height;
	private double width;
	private String color = "white";
	
	public Shape() {
		super();
	}

	public Shape(int type, double height, double width) {
		super();
		this.type = type;
		this.height = height;
		this.width = width;
	}

	public int getType() {
		return type;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public String getColor() {
		return color;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String information() {
		return this.height + " " + this.width + " " + this.color;
	}
	
	
}
