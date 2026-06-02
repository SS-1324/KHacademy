package com.kh.example.exception1;

import java.util.Scanner;

public class CharacterMenu {
	public void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		
		CharacterController cc = new CharacterController();
		
		try {
			int count = cc.countAlpha(str);
			System.out.println(str + "에 포함된 영문자 개수 : " + count);
		} catch (CharCheckException e) {
			e.printStackTrace();
		}
		
		
	}
}
