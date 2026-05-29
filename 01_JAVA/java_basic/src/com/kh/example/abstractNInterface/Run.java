package com.kh.example.abstractNInterface;

public class Run {

	public static void main(String[] args) {
		PhoneController pc = new PhoneController();
		String[] results = pc.method();
		for(String str : results) {
			System.out.println(str);
			System.out.println();
		}
	}

}
