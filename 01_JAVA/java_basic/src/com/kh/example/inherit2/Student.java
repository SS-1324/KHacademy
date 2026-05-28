package com.kh.example.inherit2;

public class Student extends Person{
	private int grade;
	private String major;
	
	Student() {
		super();
	}
	Student(String name, int age, double height, double weight, int grade, String major) {
		super(name, age, height, weight);
		this.grade = grade;
		this.major = major;
	}
	public int getGrade() {
		return grade;
	}
	public String getMajor() {
		return major;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String toString() {
		return super.toString() + ", " + this.grade + ", " + this.major;
	}
}
