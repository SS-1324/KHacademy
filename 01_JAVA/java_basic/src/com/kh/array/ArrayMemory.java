package com.kh.array;

public class ArrayMemory {

	public static void main(String[] args) {
		int i = 10;
		int[] iArr = new int[5];
		
		System.out.println(iArr.hashCode());
		System.out.println(iArr);
		//기본적으로 자바는 메모리의 주소를 직접 확인할 수 없다.
		//객체의 메모리 주소를 암호화한 해시값을 hashCode()로 가져올 수는 있다.
		
		double[] dArr = new double[3];
		System.out.println(dArr.hashCode());
		
		/*
		 	리터럴값을 바로 담을 수 있는 변수 -> 일반변수
		 	다른 메모리의 주소값을 담고있는 변수 -> 참조변수
		 	
		 	기본자료형(원시타입) : int, double, float, char, long ...
		 	-> 실제 리터럴값을 직접 담을 수 있는 변수타입
		 	
		 	그외 객체형(참조타입) : String, Scanner, int[], double[] ...
		 	-> 필요한 메모리의 크기가 가변적이기 때문에.
		 */
		
		//iArr 전체출력
		for(int j=0; j<iArr.length; j++) {
			int num = iArr[j];
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		//dArr 전체출력
		for(double num : dArr) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		//배열생성시 따로 초기화하지 않아도 기본값이 다겨있음.
		//Heap이라는 메모리공간의 특성상 빈공간을 허용하지 않기 때문.
		//-> 메모리가 할당될 때 JVM에서 기본값으로 모두 초기화.
		
		System.out.println(iArr); // 참조변수 출력시 자료형 + @ + 주소의 16진수 해시값
		iArr = null; //객체타입의 참조변수에 빈값을 표현할 때 null을 사용.
		System.out.println(iArr);
		
		//System.out.println(iArr.length);
		//System.out.println(iArr.hashCode());
		//. : 접근연산자
		//NullPointerException -> 참조변수가 가지고있는 주소값이 없음.
		
		int[] arr = new int[5]; // index -> 0~4
		//System.out.println(arr[5]);
		//ArrayIndexOutOfBoundsException
		//배열의 크기를 벚어난 index를 제시하면 발생하는 에러
		
		//배열의 가장 큰 단점.
		//배열은 한번 지정하면 크기변경이 불가.
		System.out.println(arr.hashCode());
		arr = new int[10];
		System.out.println(arr.hashCode());
		//주소값이 변경된 걸 확인할 수 있다 -> 수정이 불가하여 새로운 메모리 공간을 할당.
	
		/*
		 	Heap메모리의 데이터 중 호출이 불가한 값(연결이 끊긴 값)은
		 	더이상 쓸모가 없다.
		 	-> 일정시간이 지나면 GC(가비지컬렉터)가 알아서 회수한다.
		 	-> 자바에서의 "자동메모리관리" 특정 -> 개발자가 코드에 집중할 수 있게 해줌.
		 */
		
		arr = null;
		//해당 메모리를 더이상 사용하지 않음 -> 메모리 반환
		
		int[] arr4 = {1,1,1,1,1};
		int[] arr5 = {1,1,1,1,1};
		
		System.out.println(arr4 == arr5);
		//false -> 각 참조변수는 ==통해서 비교시 주소값비교를 하기 때문에
		
		arr4 = arr5;
		System.out.println(arr4 == arr5);
		//각각의 참조변수가 동일한 주소값을 가지고 있을 수 있음.
		
		arr4[0] = 100;
		arr5[1] = 200;
		
		for(int n : arr4) {
			System.out.print(n + " ");
		}
		System.out.println();
		for(int n : arr5) {
			System.out.print(n + " ");
		}
	}

}
