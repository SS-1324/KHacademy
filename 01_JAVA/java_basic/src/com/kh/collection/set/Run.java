package com.kh.collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Run {
	/*
	  Set
	  중복이 허용되지 않고, 순서가 없는 자료구조.
	  index개념이 없어서 위치기반 접근이 불가하다.
	  - HashSet: 일반적인 해시알고리즘 기반의 set
	  - LinkedHashSet: HashSet + 순서 유지
	  - TreeSet: 자동정렬 기능 제공
	 */

	public static void main(String[] args) {
		//생성
		Set<Human> set = new HashSet<>(); //제네릭 사용 가능.
		
		//데이터 추가
		set.add(new Human("최지원", 20));
		set.add(new Human("최지투", 20));
		set.add(new Human("최지투", 25));
		set.add(new Human("최지원", 20));
		System.out.println(set);
		//set에 저장해서 사용하는 객체는 내부적으로 동일객체인지 판단여부를
		//equlas와 hashCode를 통해 진행한다. hashCode()분류 후 equlas() 비교.
		//둘다 오버라이딩이 필요하다.

		Human h1 = new Human("최지투", 20);
		Human h2 = new Human("최지투", 22);
		
		//contains() : set구조에 포함여부 확인
		System.out.println("최지투가 존재하는가? : " + set.contains(h1));
		System.out.println("최지투가 존재하는가? : " + set.contains(h2));
		
		//remove(E e) : set구조내에서 객체를 통한 제거
		set.remove(h1);
		System.out.println(set);
		System.out.println(set.size());
		
		//set의 모든 요소에 접근하는 방법
		
		//1. for each
		for(Human h : set) {
			System.out.println(h);
		}
		
		//2. List에 담아서 활용
		List<Human> list = new ArrayList<>(); //빈리스트
		list.addAll(set);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//3. Iterator(반복자 인터페이스)를 활용
		//컬렉션에 저장된 요소를 순차적으로 접근하기위해 존재하는 인터페이스
		//순서가 없는 set같은 요소를 탐색하기위해서 필요함.
		
		//hasNext() : 다음으로 확인할 요소가 있는가?
		//next() : 다음요소를 반환
		Iterator<Human> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

}
