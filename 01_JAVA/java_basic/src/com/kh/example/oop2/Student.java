package com.kh.example.oop2;

public class Student {
	//필드
	int grade;
	int classroom;
	String name;
	double height;
	char gender;
	
	{
		name = "미입력";
	}

	//생성자
	
	//메서드
	//(alt + shift + s) + r
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getClassroom() {
		return classroom;
	}
	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}

	String information() {
		return grade + ", " + classroom + ", " + name + ", " + height + ", " + gender;
	}
}
