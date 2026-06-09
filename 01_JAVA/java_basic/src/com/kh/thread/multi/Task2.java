package com.kh.thread.multi;

public class Task2 extends Thread{

	@Override
	public void run() {
		
		try {
			//1~20까지 수 중에서 홀수만 출력
			for(int i=1; i<=20; i++) {
				if(i % 2 == 1) {
					System.out.print(i + " ");
				}
				Thread.sleep(400);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
