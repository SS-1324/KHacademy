package com.kh.example.poly1;

public class AnimalManager {

	public static void main(String[] args) {
		Animal[] aniArr = new Animal[6];
		
		aniArr[0] = new Dog("강아지",2, "진돗개");
		aniArr[1] = new Cat("야옹이",1, "검정색");
		aniArr[2] = new Dog("강아쥐",3, "삽살개");
		aniArr[3] = new Dog("강아쥬",5, "진돗개");
		aniArr[4] = new Cat("야웅이",10, "횐색");
		
		for(Animal m : aniArr) {
			if(m == null)
				break;
			
			m.speak();
			if(m instanceof Dog) {
				String breed = ((Dog) m).getBreed();
				System.out.println("이 개의 견종은 "+breed+"입니다.");
			} else if(m instanceof Cat) {
				String color = ((Cat) m).getColor();
				System.out.println("이 고양이의 색상은 "+color+"입니다.");
			}
		}
	}
	

}
