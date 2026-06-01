package com.kh.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunException {
	
	//1. try~catch
	public void examException1() {
		try(Scanner sc = new Scanner(System.in);){
			System.out.print("a/b...a : ");
			int a = sc.nextInt(); 
		
			System.out.print("a/b...b : ");
			int b = sc.nextInt(); 
			
			System.out.printf("%d / %d = %d \n", a, b, a/b);
			System.out.println("계산완료");
		} catch(InputMismatchException e) {
			System.out.println("입력 오류 발생");
		} catch(ArithmeticException e) {
			System.out.println("수학적 에러 발생 : " + e.getMessage());
		} catch(Exception e) { //에러별 정확한 처리가 불가
			e.printStackTrace();
		}
		
		System.out.println("examException1() 정상 종료");
	}
	
	//2. throws : 직접 예외를 처리하는 것이 아니라 이 메서드를 호출한 곳으로 예외처리를 위임.
	public void examException2() {
		
		//Scanner처럼 입력받기위한 객체
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		br.readLine(); -> CheckedException으로 반드시 예외처리를 해야하는 메서드(강제)
		
		1.직접처리
		try {
			br.read(); //한줄씩 입력받는 메서드
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		2. 위임 
		throws를 통해서 예외를 examException2를 호출한 곳으로 전달한다.
		public void examException2() throws IOException
		*/
		
		try {
			String str = br.readLine();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void methodA() throws IOException, CustomException {
		methodB();
	}
	
	public void methodB() throws IOException, CustomException {
		//methodC();
		methodD();
	}
	
	public void methodC() throws IOException {
		//코드~~~~
		throw new IOException("숫자입력해야되는데 문자입력함");
	}
	
	public void methodD() throws CustomException {
		//코드~~
		throw new CustomException("커스텀 에러 발생");
	}
}
