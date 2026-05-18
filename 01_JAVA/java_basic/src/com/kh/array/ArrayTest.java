package com.kh.array;

import java.util.Scanner;

public class ArrayTest {
	public static void main(String[] args) {
		//1. 크기가 10인 정수형 배열 numArr을 하나 생성.
		int[] numArr = new int[10];
		
		//2. 반복문을 통해서 numArr의 모든값을 10으로 초기화
		for(int i=0; i<numArr.length; i++) {
			numArr[i] = 10;
		}
		
		//3. for-each를 통해서 모든 배열의 요소를 출력.
		for(int num : numArr) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		//4. 사용자에게 배열의 길이를 입력받아, 입력받은 크기의 문자열 배열 nameArr를 생성.
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 길이 : ");
		int size = sc.nextInt();
		
		String[] nameArr = new String[size];
		
		//5. 사용자에게 이름을 입력받아 nameArr에 할당. 
		//   -> nameArr의 모든 요소에 이름이 들어갈 수 있도록 반복
		//  [console]
		//  배열의 길이 : 3
		//  이름 입력 : xxx
		//  이름 입력 : xxx
		//  이름 입력 : xxx
		//  =====입력완료=====
		//  xxx xxx xxx
		for(int i=0; i<nameArr.length; i++) {
			System.out.print("이름 : ");
			nameArr[i] = sc.next();
		}
		
		System.out.println("===== 입력완료 =====");
		for(String name : nameArr) {
			System.out.print(name + " ");
		}
		
		//6. 사용자에게 이름을 하나 입력받아, nameArr에 동일한 이름이 있다면
		//   동일한 이름이 존재합니다. / 동일한 이름이 존재하지 않습니다.
		System.out.print("\n이름 : ");
		String name = sc.next();
		
		//동일값 찾기
		for(int i=0; i<nameArr.length; i++) {
			if(nameArr[i].equals(name)) {
				System.out.println("동일한 이름이 존재합니다.");
				break;
			} else if(i == (nameArr.length - 1)){ //마지막 값
				System.out.println("동일한 이름이 존재하지 않습니다.");
			}
		}
		
		boolean isDupl = false; //못찾음.
		for(String n : nameArr) {
			if(name.equals(n)) {
				isDupl = true;//동일값이 있다.
				break; //동일값 찾았으니 정지.
			}
		}
		// true : 동일값 존재함, false : 동일값 못찾음.
		System.out.println(isDupl ? "동일한 이름이 존재합니다." : "동일한 이름이 존재하지 않습니다.");
		
		
	}
}
