package com.kh.object.ex3;

public class Run {

	public static void main(String[] args) {
		Math m = new Math();
		System.out.println(m.adder(10));
		System.out.println(m.adder(10.0, 20.0));
		System.out.println(m.adder('a', 'b'));
		System.out.println(m.adder(10, "살 입니다"));
	}

}
