package com.kh.example.inherit2;

public class PersonController {
	private Student[] s = new Student[3];
	private Employee[] e = new Employee[10];
	
	public int[] personCount() {
		//학생수
		int stCount = 0;
		for(Student st : s) {
			if(st == null) {
				break;
			}
			stCount++;
		}
		
		//사원수
		int empCount = 0;
		for(Employee emp : e) {
			if(emp == null) {
				break;
			}
			empCount++;
		}
		
		int[] countArr = {stCount, empCount};
		return countArr;
	}
	
	public void insertStudent(String name, int age, double height, double weight, int grade, String major) {
		Student st = new Student(name, age, height, weight, grade, major);
		//앞에서부터 검삭해서 null값에 저장
		for(int i=0; i<s.length; i++) {
			if(s[i] == null) {
				s[i] = st;
				break;
			}
		}
	}
	
	public Student[] printStudent() {
		return s;
	}
	
	public void insertEmployee(String name, int age, double height, double weight, int salary, String dept) {
		for(int i=0; i<e.length; i++) {
			if(e[i] == null) {
				e[i] = new Employee(name, age, height, weight, salary, dept);
				break;
			}
		}
	}
	
	public Employee[] printEmployee() {
		return e;
	}
}
