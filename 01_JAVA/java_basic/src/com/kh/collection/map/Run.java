package com.kh.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Run {

	/*
	 	Map
	 	key와 value를 쌍으로 저장하는 구조
	 	key는 식별용도로 중복x, vlaue는 값을 저장하는 것으로 중복o
	 	HashMap(보편적임), LinkedHashMap(입력순서보장), TreeMap(정렬key 순서보장)
	 */
	public static void main(String[] args) {
		Map<Integer, Human> hMap = new HashMap<>();
		
		//1. put() : map구조에 key, value쌍으로 값을 추가.
		hMap.put(10, new Human("최지원", 20));
		hMap.put(20, new Human("최지투", 30));
		hMap.put(30, new Human("최지삼", 40));
		hMap.put(40, new Human("최지사", 50));
		hMap.put(50, new Human("최지사", 50));
		hMap.put(40, new Human("최지오", 60)); //동일 key 사용시 값을 덮어씌움
		System.out.println(hMap);//저장된 순서 안됨.

		//2. get(key) : 해당 key를 통해 value를 반환
		System.out.println(hMap.get(30));
		
		//3. size() : 담겨있는 객체 수
		System.out.println(hMap.size());
		
		//4. remove(key) : 해당 key의 값을 찾아서 제거
		hMap.remove(40);
		System.out.println(hMap.get(40));
		
		//map에 전체요소에 접근하는 방법
		//for each 이용X, index로 반복X, Iterator 이용X
		// 다른 자료구조로 변경 후 반복 -> set
		
		//1. keySet() : key를 모아서 Set자료구조 형태로 반환
		Set<Integer> ks = hMap.keySet(); // ks = key만 모아둔 Set구조(10,20,30,50)
		System.out.println(ks);
		for(Integer key : ks) {
			System.out.println("키 : " + key + " 값 : " + hMap.get(key));
		}
		
		//2. entrySet을 이용한 전체탐색
		//Map은 key와 value를 쌍으로 저장 -> keySet()으로는 key만 가져옴.
		//set이지만 key, value를 저장하는 하나의 자료구조
		//Set<Map.Entry<K,V>>
		Set<Entry<Integer, Human>> entrySet = hMap.entrySet();
		for(Entry<Integer, Human> e : entrySet) {
			System.out.println("키 : " + e.getKey() + " 값 : " + e.getValue());
		}
	}

}
