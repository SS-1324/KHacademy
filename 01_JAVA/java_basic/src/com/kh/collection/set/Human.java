package com.kh.collection.set;

import java.util.Objects;

public class Human {
	private String name;
	private int age;
	
	Human() {
		super();
	}
	Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//toString 단축키 : (alt + shift + s) + s
	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}
	
	//Object의 equals를 재정의
	//객체생성시 객체비교를 할 일이 있는 객체라면 equals를 재정의해야함.
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Human) { //주소값은 다르지만 Human객체라면 필드비교를 통해 확인
			Human h = (Human)obj;
			return this.name.equals(h.getName()) && this.age == h.getAge();
		}
		
		return super.equals(obj);
	}
	
	
}
