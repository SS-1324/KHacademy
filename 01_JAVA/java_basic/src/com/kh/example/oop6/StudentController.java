package com.kh.example.oop6;

public class StudentController {
	private Student[] sArr = new Student[5];
	public static final int CUT_LINE = 60;
	
	public StudentController() {
		super();
		
		sArr[0] = new Student("김길동", "자바", 100);
		sArr[1] = new Student("박길동", "디비", 50);
		sArr[2] = new Student("이길동", "화면", 85);
		sArr[3] = new Student("정길동", "서버", 60);
		
	}
	
	public Student[] printStudent() {
		return sArr;
	}
	
	public int sumScore() {
		int sum = 0;
		for(Student st : sArr) {
			if(st == null)
				break;
			sum += st.getScore();
		}
		
		return sum;
	}
	
	//double형 배열에 점수합, 점수평균을 담아 반환
	public double[] avgScore() {
		double[] scoreArr = new double[2];
		int sum = this.sumScore();
		scoreArr[0] = sum;
		
		int count = 0; //학생수
		for(Student st : sArr) {
			if(st == null)
				break;
			count++;
		}
		
		scoreArr[1] = (double)sum / count;
		
		return scoreArr;
	}
	
	
}
