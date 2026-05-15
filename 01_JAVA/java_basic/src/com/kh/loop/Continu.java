package com.kh.loop;

public class Continu {

	//continue : 반복문 안에서 사용되는 키워드
	//			 continue실행시 즉시 다음 반복으로 이동함.
	public static void main(String[] args) {
		
		// 1~50까지의 수 중에서 5의 배수만 출력
		for(int i=1; i<51; i++) {
		    if(i / 5 != 0) { // 5로 나눈 나머지가 0이 아니라면 (5의 배수가 아니라면)
		        continue;
		    }
		    System.out.print(i + " ");
		}

	}

}
