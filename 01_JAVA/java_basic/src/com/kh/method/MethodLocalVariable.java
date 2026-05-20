package com.kh.method;

public class MethodLocalVariable {
	/*
	 	지역변수
	 	- 블록({})내부에서 선언된 변수
	 	- 블록이 종료될 때 메모리에서 제거됨.
	 	- 선언시 반드시 초기화 후에 사용해야됨.
	 	
	 	매개변수
	 	- 메서드 호출시 외부에서 전달받은 값을 저장하는 지역변수
	 	- 메서드 선언부에 필요에 따라 정의됨.
	 */

	public static void main(String[] args) {
		//main의 변수 -> main이 종료되면 사라짐.
		String name = "지원";
		int age = 15;
		
		System.out.println("say호출 전 : " + name);
		say(name, age);
		System.out.println("say호출 후 : " + name);
		
		//call by value(값에 의한 호출) -> 자바는 무조건 이방식으로 호출
		//메모리에 담긴 값 자체를 전달
		//원시타입 -> 값(데이터)자체가 전달됨.
		//참조타입 -> 참조값(주소값)이 전달됨.
		//          주소값이 전달된다는 건, 같은 메모리를 가르키게 되므로, 
		//			내부 데이터를 변경시에 원본에 영향을 줌.
		
		//기본형
		int num = 10;
		changeValue(num);
		System.out.println("main에서 changeValue 호출 후 : " + num);
		
		//참조형
		int[] arr = {1,2,3};
		changeReference(arr);
		
		System.out.println("====== main의 arr =====");
		for(int n : arr) {
			System.out.print(n + " ");
		}
		
		/*
		 	참조타입의 값을 전달하면 실제 값 자체를 전달하는게 아니라
		 	변수에 담긴 주소값이 전달되기 때문에
		 	해당 주소로 접근해서 원본데이터를 변경할 수 있다.
		 	-> 함수에서 원본데이터를 변경하는 것은 추후 문제를 일으킬 수 있다.
		 */
	}
	
	public static void changeReference(int[] array) {
		System.out.println("====== 전달받은 array =====");
		for(int n : array) {
			System.out.print(n + " ");
		}
		
		System.out.println();
		array[0] = 100;
		System.out.println("array[0] 변경완료");
	}
	
	public static void changeValue(int value) {
		System.out.println("전달받은 value : " + value);
		value = 100;
		System.out.println("변경된 value : " + value);
	}
	
	public static void say(String name, int age) {
		name = "수민";
		System.out.println("say 내부 : " + name);
	}

}
