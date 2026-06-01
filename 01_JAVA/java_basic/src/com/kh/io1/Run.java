package com.kh.io1;

import java.io.File;
import java.io.IOException;

public class Run {
	/*
	 	간단하게 파일/폴더 생성, 정보 확인
	 	java.io.File class를 활용 -> 자바코드에서 파일/폴더/경로를 다루는 객체
	 	
	 	상대경로
	 	지금 내 위치를 기반으로 다음 경로를 탐색
	 	ex) 앞으로 1분전진 -> 오른쪽으로 돌아서 1분 전진
	 	./~   : .(지금 내 위치) 
	 	
	 	 
	 	절대경로
	 	특정 기준점을 바탕으로 정해진 경로
	 	ex) 서울 강남구 논현로87길 41 1층 104-1호
	 	C://~ D://~ , /root/~
	 */
	public static void main(String[] args) {
		//1. 경로지정없이 객체를 생성(상대경로 - 현재 작업 폴더 기준)
		File f1 = new File("test.txt");
		//2. 실제 존재하는 경로에 파일 생성(절대경로)
		File f2 = new File("D:\\test.txt");
		
		File f3 = new File("D:\\tmp\\test.txt");
		
		try {
			f1.createNewFile(); //f1파일객체의 정보를 기준으로 실제 파일을 만들어 줘.
			f2.createNewFile();
			//f3.createNewFile(); 존재하지 않는 경로로 생성시 예외 -> IOException
			
			File tmpFolder = new File("D:\\tmp");
			tmpFolder.mkdir(); //폴더생성 함수
			f3.createNewFile(); 
			
			//먼저 경로에 있는 파일이 있는지 확인하고 있다면 해당 파일의 정보를 가져와서 객체를 만듬.
			File f4 = new File("ttt.txt");
			
			System.out.println("파일존재여부 : " + f4.exists());
			
			System.out.println("파일여부 : " + tmpFolder.isFile());
			System.out.println("파일여부 : " + f4.isFile());
			
			System.out.println("파일명 : " + f4.getName());
			System.out.println("파일경로 : " + f4.getAbsolutePath());
			System.out.println("파일 용량 : " + f4.length());
			System.out.println("파일의 상위폴더 : " + f4.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
