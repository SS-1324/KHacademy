package com.kh.array.practice;

import java.util.Scanner;

public class Practice8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 	f f f f f...
		 	f f f f f...
		 	f f t t t...
		 	f f t t t...
		 	...
		 */
		int[][] paper = new int[100][100];
		int n = sc.nextInt(); // 색종이를 붙일 횟수
		
		for(int k=0; k<n; k++) {
			int x = sc.nextInt() - 1; //왼쪽으로부터 색종이와의 거리(열)
			int y = sc.nextInt() - 1; //아래로부터 색종이와의 거리(행)
			
			for(int j=y; j<y+10; j++) {
				for(int i=x; i<x+10; i++) {
					paper[i][j] = 1;
				}
			}
		}
		
		int area = 0;
		for(int i=0; i<paper.length; i++) {
			for(int j=0; j<paper[i].length; j++) {
				if(paper[i][j] == 1) {
					area++;
				}
			}
		}
		
		System.out.println(area);
	}

}
