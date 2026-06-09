package com.kh.thread.runable;

//스레드를 만드는 방법1. Runnalbe 인터페이스를 구현
public class Task1 implements Runnable{

	//run()에 스레드에서 실행하고자하는 코드를 기술
	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;
		
		//Thread.currentThread() -> 지금 실해중인 스레드 정보 가져오기
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1 + n2));
		
	}

}
