package com.kh.example.oop4;

import java.util.Scanner;

public class ShapeMenu {
	//필드
	private Scanner sc = new Scanner(System.in);
	private SquareController scr = new SquareController();
	private TriangleController tc = new TriangleController();
	
	//메서드
	public void inputMenu() {
		
		while(true) {
			System.out.println("===== 도형 프로그램 =====");
			System.out.println("3. 삼각형");
			System.out.println("4. 사각형");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 :");
			
			int select = sc.nextInt();
			
			switch(select) {
			case 3:
				this.triangleMenu();
				break;
			case 4:
				this.squareMenu();
				break;
			case 9:
				System.out.println("프로그램 종료");
				return; //반환 + 함수종료
			default: 
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
			System.out.println();
		}
	}
	
	public void triangleMenu() {
		while(true){
			System.out.println();
			System.out.println("===== 삼각형 =====");
			System.out.println("1. 삼각형 면적");
			System.out.println("2. 삼각형 색칠");
			System.out.println("3. 삼각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 :");
			int select = sc.nextInt();
			
			switch(select) {
			case 1:
			case 2:
				this.inputSize(3, select);
				break;
			case 3:
				this.printInformation(3);
				break;
			case 9:
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}
		
	}

    public void squareMenu() {
    	while(true) {
	    	System.out.println();
	    	System.out.println("===== 사각형 =====");
			System.out.println("1. 사각형 둘레");
			System.out.println("2. 사각형 면적");
			System.out.println("3. 사각형 색칠");
			System.out.println("4. 사각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 :");
			
			int select = sc.nextInt();
			
			switch(select) {
			case 1:
			case 2:
			case 3:
				this.inputSize(4, select);
				break;
			case 4:
				this.printInformation(4);
				break;
			case 9:
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
    	}
    }
    
    //type : 3 = 삼각형, 4 = 사각형
    //menuNum : 각 도형에서 선택한 메뉴번호
    public void inputSize(int type, int menuNum) {
    	switch(type) {
    	case 3: //삼각형
    		switch(menuNum) {
	    		case 1:{ //면적
	    			System.out.print("높이 : ");
	    			int height = sc.nextInt();
	    			System.out.print("너비 : ");
	    			int width = sc.nextInt();
	    			
	    			double area = tc.calcArea(height, width);
	    			System.out.println("삼각형의 면적 : " + area);
	    		}break;
	    		case 2: {//색칠
	    			System.out.print("색깔을 입력하세요 : ");
	    			String color = sc.next();
	    			tc.paintColor(color);
	    		}
    		} break;
    	case 4: //사각형
    		switch(menuNum) {
	    		case 1: //둘레
	    		{
	    			System.out.print("높이 : ");
	    			int height = sc.nextInt();
	    			System.out.print("너비 : ");
	    			int width = sc.nextInt();
	    			
	    			double perimeter = scr.calcPerimeter(height, width);
	    			System.out.println("사각형의 둘레 : " + perimeter);
	    		}break;
	    		case 2:{ //면적
	    			System.out.print("높이 : ");
	    			int height = sc.nextInt();
	    			System.out.print("너비 : ");
	    			int width = sc.nextInt();
	    			
	    			double area = scr.calcArea(height, width);
	    			System.out.println("사각형의 면적 : " + area);
	    		}break;
	    		case 3: {//색칠
	    			System.out.print("색깔을 입력하세요 : ");
	    			String color = sc.next();
	    			scr.paintColor(color);
	    		}
    		}
    	}
    }
    
    public void printInformation(int type) {
    	String info = (type == 3) ? tc.print() : scr.print();
    	System.out.println(info);
    }
   
   
}
