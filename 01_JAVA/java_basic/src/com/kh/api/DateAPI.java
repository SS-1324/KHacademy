package com.kh.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateAPI {
	public void method() {
		/*
		 	1. java.util.Date
		 	
		 	자바초기부터 사용하던 날짜/시간 클래스.
		 	현재는 직접 사용을 권장하지 않음.
		 	
		 	이유:
		 	- 연도 계산이 불편함 : 실제연도 - 1900
		 	- 월 계산이 불편함 : 실제 월 - 1
		 	- 날짜 변경이 가능해서 안전하지 않음.
		 	- getter/setter 대부분 deprecated 처리됨(예전에는 많이썼는데 이제는 딱히 권장하지않아.)
		 
		 */
		
		Date date1 = new Date();
		// 현재 컴퓨터의 날짜와 시간이 들어감.
		
		System.out.println("현재 Date : " + date1);
		
		//원하는 날짜 만들기
		// 2026년 3월 1일
		// 연도: 2026 - 1900
		// 월 : 3 - 1
		// 일 : 1
		
		Date date2 = new Date(2026 - 1900, 3 - 1, 1);
		System.out.println("지정한 날짜 : " + date2);
		
		//날짜, 시간을 변경
		//setter를 사용해서 변경
		
		date1.setYear(2025 - 1900);
		date1.setMonth(5 - 1);
		date1.setDate(2);
		date1.setHours(12);
		date1.setMinutes(30);
		date1.setSeconds(15);
		
		System.out.println("변경된 Date : " + date1);
		System.out.println("연도 : " + (date1.getYear() + 1900));
		
		/*
		 	2. LocalDateTime
		 	
		 	java8부터 도입된 날짜/시간 클래스
		 	날짜와 시간을 함께 표현함
		 	
		 	특징:
		 	- 연도, 월을 있는 그대로 사용함.
		 	- 날짜/시간 계산이 편함
		 	- 객체가 불변이라 안점함
		 	- 실제로 많이 씀.
		 */
		
		LocalDateTime now = LocalDateTime.now();
		//현재 날짜와 시간
		
		System.out.println("날짜및 시간 : " + now);
		
		//특정 날짜와 시간으로 생성
		//2025년 5월 25일 12시 30분 15초
		LocalDateTime date3 = LocalDateTime.of(2026, 5, 25, 12, 30, 15);
		System.out.println("날짜및 시간 : " + date3);
		
		/*
		 	날짜/시간 계산
		 	
		 	pulsDays(10) : 10일뒤
		 	minusHours(5) : 5시간 전
		 	
		 	LocalDateTime은 원본객체를 바꾸지않고 
		 	계산 결과를 새로운 객체로 반환
		 */
		System.out.println("17일 뒤 : " + date3.plusDays(17));
		System.out.println("원본 : " + date3);
		
		/* 날짜와 시간 분리*/
		LocalDate onlyDate = date3.toLocalDate();
		LocalTime onlyTime = date3.toLocalTime();
		
		System.out.println("날짜 : " + onlyDate);
		System.out.println("시간 : " + onlyTime);
		
		/*
		 	날짜/시간 포맷팅
		 	
		 	yyyy: 연도
		 	MM: 월
		 	dd: 일
		 	HH: 시
		 	mm: 분
		 	ss: 초
		 */
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String formatDate = date3.format(formatter);
		System.out.println(formatDate);
	}

}
