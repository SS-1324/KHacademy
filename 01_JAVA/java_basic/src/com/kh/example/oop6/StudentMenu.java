package com.kh.example.oop6;

public class StudentMenu {
	private StudentController ssm = new StudentController();

	public StudentMenu() {
		super();
		
		Student[] sArr = ssm.printStudent();
		
		//학생 정보 출력
		System.out.println("==========학생 정보 출력==========");
		for(Student st : sArr) {
			if(st == null)
				break;
			String info = st.inform();
			System.out.println(info);
		}
		
		//학생 성적 출력
		System.out.println("==========학생 성적 출력==========");
		double[] scoreArr = ssm.avgScore();
		System.out.println("학생 점수 합계 : " + scoreArr[0]);
		System.out.println("학생 점수 평균 : " + scoreArr[1]);
		
		//성적 결과 출력
		System.out.println("==========성적 결과 출력==========");
		for(Student st : sArr) {
			if(st == null)
				break;
			//김길동학생은 통과입니다.
			//박길동학생은 재시험 대상입니다.
			if(st.getScore() < StudentController.CUT_LINE) {
				System.out.println(st.getName() + "학생은 재시험 대상입니다.");
			} else {
				System.out.println(st.getName() + "통과입니다.");
			}
		}
	}
	
	
}
