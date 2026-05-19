package com.kh.array;

import java.util.Scanner;

public class Array2DTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 	사용자에게 행(m)과 열(n)을 입력받아
		 	m행 n열의 빙고판을 만들어라.
		 	행과 열의 들어갈 문자를 각각 입력받아 저장한 뒤
		 	출력해라
		 	
		 	[console]
		 	행: 2
		 	열: 3
		 	
		 	1행1열 : 바나나
		 	1행2열 : 배
		 	...
		 	2행3열 : 키위
		 	
		 	바나나 배 딸기
		 	귤 수박 키위
		 */
		/*
		System.out.print("행 : ");
		int m = sc.nextInt();
		System.out.print("열 : ");
		int n = sc.nextInt();
		
		String[][] board = new String[m][n];
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				System.out.printf("%d행%d열 : ", i + 1, j + 1);
				board[i][j] = sc.next();
			}
		}
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		/*
		 	사용자에게 좌석의 행과 열을 입력받아 좌석을 생성한다. 
		 	각 좌석에 들어갈 관객의 이름을 입력받아 저장한 뒤
		 	모두 입력받았으면 좌석표를 출력해라.
		 	
		 	[console]
		 	행(줄)의 수: 2
		 	열(좌석)의 수: 3
		 	1행 1열 : 철수
		 	1행 2열 : 민수
		 	...
		 	========= 좌석표 =========
		 	철수 민수 ...
		 */
		System.out.print("행(줄)의 수: ");
		int m = sc.nextInt();
		
		System.out.print("열(좌석)의 수: ");
		int n = sc.nextInt();
		
		String[][] seats = new String[m][n];
		for(int i=0; i<seats.length; i++) {
			for(int j=0; j<seats[i].length; j++) {
				System.out.printf("%d행 %d열 : ", i+1, j+1);
				seats[i][j] = sc.next();
			}
		}
		
		for(int i=0; i<seats.length; i++) {
			for(int j=0; j<seats[i].length; j++) {
				System.out.print(seats[i][j] + " ");
			}
			System.out.println();
		}
	}

}
