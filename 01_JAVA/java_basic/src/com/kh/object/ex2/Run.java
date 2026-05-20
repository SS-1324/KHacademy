package com.kh.object.ex2;

public class Run {

	public static void main(String[] args) {
		BankAccount ac1 = new BankAccount();
		ac1.acoountNumber = "ac1111";
		BankAccount ac2 = new BankAccount();
		ac2.acoountNumber = "ac1112";
		
		ac1.deposit(5000);
		ac2.deposit(2000);
		
		ac1.withdraw(1000);
		ac2.withdraw(500);
		
		ac1.checkMyBalance();
		ac2.checkMyBalance();
	}

}
