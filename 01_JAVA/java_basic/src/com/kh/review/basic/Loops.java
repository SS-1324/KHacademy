package com.kh.review.basic;
/*
 * 반복문
 * 같은 일을 여러번 시키는 방법
 * 종류가 여러개 있지만 기준은 단순함.
 * 
 * - 횟수가 정해진 반복 : for
 * - 조건이 참인 동안만 반복 : while
 * + 일단 한번 실행하고 조건에 따라 반복 : do ~ while
 */
public class Loops {
	public static void main(String[] args) {
		// for문 - 횟수가 정해진 반복에 사용
		// for(시작 ; 조건 ; 변화) {반복할 코드}
		// 1.시작값 -> 2. 조건검사 -> 3. 반복할코드 -> 4. 변화 -> 2 ...
		for(int i=1; i<=5; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		/*
			for문에서 많이 사용하는 공식
			
			n번반복
			for(int i=0; i<n ; i++) {
				반복할 코드
			}
			
			문자열의 길이만큼 반복
			for(int i=0; i<문자열.length() ; i++) {
				반복할 코드
			}
			
			배열의 길이만큼 반복
			for(int i=0; i<배열.length ; i++) {
				반복할 코드
			}
		*/
		
		System.out.println("--------------------------------------");
		// while문 - 조건이 참인 동안만 반복
		// 횟수보다 '언제 멈출 것인가'를 중요하게 봐야함.
		int count = 5;
		while(count > 0) {
			System.out.println(count + " ");
			count--; //count를 1씩 줄이면서 반복조건탈출을 만들어 줌.
		}
		
		count = 5;
		do {
			System.out.println(count + " ");
			count--; //count를 1씩 줄이면서 반복조건탈출을 만들어 줌.
		} while(count > 0);
		
		
		// 모든코드는 중첩이 가능하다
		// 반복문이 중첩되면 바깥쪽 반복이 한번 돌때
		// 안쪽 반복은 통째로 다 한번 돈다.
		//5번 반복 하면서 각 9번반복 -> 1~5단
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				System.out.println((i + 1) + " * " + (j+1));
			}
		}
		
		// break / continue - 반복에 흐름을 조절
		// break: 반복을 즉시 중단
		// continue: 즉시 다음 반복으로 진행
		
		System.out.println("------1~100 중 짝수만 출력-----------");
		for(int i=1; i<=100; i++) {
			if(i % 2 == 1) { //홀수일때
				continue;
			}
			
			System.out.println(i);
		}
		System.out.println();
		
		//숫자를 1부터 100까지 반복하다가 15일때 중단
		for(int i=1; i<=100; i++) {
			if(i == 15) {
				break;
			}
			System.out.println(i);
		}
		
		//향상된 for문 : for-each
		//배열이나 컬렉션같은 것들을 요소를 전부 하나씩 가지고와서 반복하기위해 사용.
		int[] scores = {10,20,30,40,50};
		for(int i=0; i<scores.length; i++) {
			System.out.println(scores[i]);
		}
		
		for(int s : scores) {
			System.out.println(s);
		}
	}
}
