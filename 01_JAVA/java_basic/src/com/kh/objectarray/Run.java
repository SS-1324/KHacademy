package com.kh.objectarray;

import java.util.Scanner;

public class Run {
	//객체배열
	/*
	 	같은 타입의 객체 여러개를 하나의 배열의 저장해서 관리하는 것
	 	단, 배열의 각 요소에 객체의 주소를 저장하는 참조변수가 들어있다.
	 	   배열 생성시에 객체까지 자동으로 생성되는게 아니라, 각 요소에 객체를 직접 생성해야한다.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//1. 객체배열 선언및 생성
		Book[] bArr = new Book[3]; // bArr[0] = null ...
		//Book b1 = null, b2 = null, b3 = null;
		
		//2. 요소별 객체생성
		for(int i=0; i<bArr.length; i++) {
			System.out.print("제목 : ");
			String title = sc.next();
			
			System.out.print("장르 : ");
			String genre = sc.next();
			
			System.out.print("저자 : ");
			String author = sc.next();
			
			System.out.print("페이지 수 : ");
			int maxPage = sc.nextInt();
			
			bArr[i] = new Book(title, genre, author, maxPage);
		}
		
		//3. 요소별 객체 출력
		for(Book b : bArr) {
			System.out.println(b.toString());
		}
	}

}
