package com.kh.polymorphism;

public class Avante extends Car{

	Avante(String color, String fuel, int year) {
		super(color, fuel, year);
	}

	@Override
	public void drive() {
		System.out.println("슈웅~ 슈웅~"); 
	}
	
	public void moveAvante() {
		System.out.println("빵빵~ Avante");
	}
	
	public void driveCar(Car car) {
		// car의 메모리에 실제로 Avante가 있는가?
		if(car instanceof Avante) {
			((Avante)car).moveAvante();
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Avante";
	}
	
}
