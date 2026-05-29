package com.kh.polymorphism;

public class Run {

	public static void main(String[] args) {
		/*
		 	클래스 참조변수이름				= 			new 클래스 생성자();
		 	(부모클래스가 위치할 수 있음)				(자식 클래스가 위치할 수 있음)
		 	실제로 어디까지 접근이 가능한가			실제로 어떤형태의 메모리가 생성되는가
		 */
		/*
		Cake c1 = new CheeseCake();
		//c1은 Cake의 메모리까지 접근이 가능하며
		//실제로는 CheeseCake의 메모리 공간까지 존재한다.
		
		//c1.milky(); 접근범위를 넘어선다.
		c1.sweet();
		//오버라이딩시에는 실제 메모리에 있는 재정의된 메서드가 호출됨.
		c1.yummy();
		
		//CheeseCake c2 = new Cake();
		//실제 메모리가 Cake이므로 CheeseCake공간에 접근할 수 없음.
		//-> 접근범위가 실제 메모리크기보다 클 수 없다.
		
		//CheeseCake c2 = c1;
		//c1은 Cake타입이므로 실제 메모리가 어떤지는 알 수 없고 Cake까지만 사용이 되는 걸로 판단됨.
		//CheeseCake의 메모리공간을 사용할 수 있다는게 보장되지 않기때문에 에러발생
		//
		*/
		
		//1. 부모타입의 참조변수로 부모객체를 다루는 경우
		Car c1 = new Car("빨간색", "가솔린", 2021);
		c1.drive();
		//((Sonata)c1).moveSonata();
		
		//2. 자식타입 참조변수에 자식객체를 다루는 경우
		Avante c2 = new Avante("횐색", "LPG", 2023);
		c2.drive(); //Avante의 오버라이딩된 drive()가 호출
		c2.moveAvante();
		Car c3 = c2; // 부모참조변수로 업캐스팅 가능
		c3.drive();//오버라딩된 메서드는 생성된 메모리를 기준으로 호출됨.
		//((Car)c2).drive();
		//c3.moveAvante(); 업캐스팅시 자식요소의 메모리공간 접근 불가

		//3. 부모타입의 참조변수로 자식객체를 다루는 경우(업캐스팅)
		Car c4 = new Sonata("검정", "디젤", 2024);
		//c4.moveSonata(); 참조변수가 부모타입이므로 자식요소의 메서드 접근 불가
		((Sonata)c4).moveSonata();
		
		/*
		 	"상속구조"의 클래스들 간의 형변환 가능
		 	1. UpCasting(업캐스팅)
		 	   자식타입 -> 부모타입으로 형변환
		 	   자동형변환
		 	   ex) Car c = new Sonata();
		 	
		 	2. DownCasting(다운캐스팅)
		 	   부모타입 -> 자식타입으로 형변환
		 	   강제형변환 해야됨, 업캐스팅관계에 있을 때(실제 메모리가 자식타입일 때)만 가능.
		 	   ex) ((Sonata)c).moveSonata();
		 */
		
		c2.driveCar(c1);
		c2.driveCar(c2);
		c2.driveCar(c3);
		
		System.out.println("==========================");
		
		//기본적으로 참조변수 출력시 무조건 toString() 호출됨.
		System.out.println(c1.toString());
		System.out.println(c1);
		System.out.println(c2);
	}

}
