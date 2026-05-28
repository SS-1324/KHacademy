package com.kh.example.inherit;

import java.util.Scanner;

public class PointMenu {
	private Scanner sc = new Scanner(System.in);
	private CircleController cc = new CircleController();
	private RectangleController rc = new RectangleController();
	
	public void mainMenu() {
		while(true) {
			System.out.println();
			System.out.println("===== 메뉴 =====");
			System.out.println("1. 원");
			System.out.println("2. 사각형");
			System.out.println("9. 끝내기");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			
			switch(sel) {
			case 1:
				this.circleMenu();
				break;
			case 2:
				this.rectangleMenu();
				break;
			case 9:
				System.out.println("종료합니다.");
				return;
			default :
				System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
			}	
		}
	}
	
	public void circleMenu() {
		while(true) {
			System.out.println();
			System.out.println("===== 원 메뉴 =====");
			System.out.println("1. 원 둘레");
			System.out.println("2. 원 넓이");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			
			switch(sel) {
			case 1:
				this.calcCircum();
				break;
			case 2:
				this.calcCircleArea();
				break;
			case 9:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void rectangleMenu() {
		while(true) {
			System.out.println();
			System.out.println("===== 사각형 메뉴 =====");
			System.out.println("1. 사각형 둘레");
			System.out.println("2. 사각형 넓이");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int sel = sc.nextInt();
			
			switch(sel) {
			case 1:
				this.calcPerimeter();
				break;
			case 2:
				this.calcRectArea();
				break;
			case 9:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}	
		}
	}
	
	public void calcCircum() {
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		
		System.out.print("반지름 : ");
		int r = sc.nextInt();
		
		String info = cc.calcCircum(x, y, r);
		System.out.println("둘레 : " + info);
	}
	
	public void calcCircleArea() {
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		
		System.out.print("반지름 : ");
		int r = sc.nextInt();
		
		String info = cc.calcArea(x, y, r);
		System.out.println("면적 : " + info);
	}
	
	public void calcPerimeter() {
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		
		System.out.print("높이 : ");
		int h = sc.nextInt();
		
		System.out.print("너비 : ");
		int w = sc.nextInt();
		
		String info = rc.calcPerimeter(x, y, h, w);
		System.out.println("둘레 : " + info);
	}
	
	public void calcRectArea() {
		System.out.print("x 좌표 : ");
		int x = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int y = sc.nextInt();
		
		System.out.print("높이 : ");
		int h = sc.nextInt();
		
		System.out.print("너비 : ");
		int w = sc.nextInt();
		
		String info = rc.calcArea(x, y, h, w);
		System.out.println("면적 : " + info);
	}
}
