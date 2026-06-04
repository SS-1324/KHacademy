package com.kh.generic;

// <T> : T라고 우선 쓴다음 객체생성시에 타입을 정하겠다.
public class Box<T> {
	private T value;
	private T[] arr;
	private int num;
	
	Box() {
		super();
	}
	Box(T value, T[] arr, int num) {
		super();
		this.value = value;
		this.arr = arr;
		this.num = num;
	}
	
	public T getValue() {
		return value;
	}
	public T[] getArr() {
		return arr;
	}
	public int getNum() {
		return num;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public void setArr(T[] arr) {
		this.arr = arr;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
