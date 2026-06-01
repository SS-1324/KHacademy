package com.kh.example.poly2;

public class Member {
	private String name;
	private int age;
	private char gender;
	private int couponCount = 0;
	
	Member() {
		super();
	}
	Member(String name, int age, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public char getGender() {
		return gender;
	}
	public int getCouponCount() {
		return couponCount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}
	
	@Override
	public String toString() {
		String str = "Member [name="+name+", age="+age+", gender="+gender+", couponCount="+couponCount+"]";
		return str;
	}
	
	
}
