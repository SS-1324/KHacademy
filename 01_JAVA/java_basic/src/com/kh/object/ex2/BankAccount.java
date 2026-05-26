package com.kh.object.ex2;

public class BankAccount {
	//필드
	int balance;
	String accountNumber;
	
	//생성자
	/*
	 	기본생성장 : 매개변수가 없는 생성자
	 	- 개발자가 생성자를 정의하지 않으면 컴파일과정에서 자동으로 생성
	 	- 생성자를 하나라도 정의하면 기본생성자는 생성되지 않는다.
	 */
	public BankAccount() {
		System.out.println("생성자 실행됨.");
	}
	
	public BankAccount(int bal, String acc) {
		balance = bal;
		accountNumber = acc;
	}
	
	//메서드
	//입금
	void deposit(int amount) {
		balance += amount;
	}
	
	//출금
	void withdraw(int amount) {
		balance -= amount;
	}
	
	//잔액조회
	void checkMyBalance() {
		System.out.println("잔액 : " + balance);
	}
}
