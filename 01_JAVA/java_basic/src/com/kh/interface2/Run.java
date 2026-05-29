package com.kh.interface2;

public class Run {
	/*
	 인터페이스 : 클래스가 구현해야 할 메서드(추상)를 정의해 둔 것
	 - 추상메서드만 선언 가능(구현부가 없는 메서드)
	 - java8이후에 default메서드, static메서드도 사용 가능
	 - 모든 메서드는 묵시적으로 public abstract키워드가 자동 생성됨
	 - 모든 변수는 묵시적으로 public static final키워드가 자동 생성됨
	 - 인터페이스를 구현하는 class는 반드시 인터페이스의 모든 추상메서드를 구현(오버라이딩)
	 - 자바에서 클래스는 다중상속 불가, 인터페이스는 다중상속, 다중구현 가능
	 
	 장점
	 1. 다형성 제공(인터페이스를 참조타입으로 사용해서 여러 객체를 구현 할 수 있음)
	 2. 기능이 표준화(기능을 미리 추상메서드로 정의해서 형식을 통일)
	 3. 코드 결합도 낮아짐(구현체를 자유롭게 변경)
	 */
	public static void main(String[] args) {
		Animal cat = new Cat();
		cat.speak();
		cat.breathe();
	}

}
