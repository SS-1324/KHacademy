package com.kh.polymorphism;

public class Car {
	private String color;
	private String fuel;
	private int year;
	
	Car() {
		super();
	}
	Car(String color, String fuel, int year) {
		super();
		this.color = color;
		this.fuel = fuel;
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public String getFuel() {
		return fuel;
	}
	public int getYear() {
		return year;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public void drive() {
		System.out.println("부릉부릉~");
	}
	// (alt +shift + s) -> v
	//Object에 있는 toString()메서드를 오버라이딩한다.
	@Override
	public String toString() {
		return this.color + ", " + this.fuel + ", " + this.year;
	}
	
}
