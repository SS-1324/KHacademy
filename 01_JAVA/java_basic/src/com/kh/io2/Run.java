package com.kh.io2;

public class Run {
	/*
	 	자바의 스트림 개념
	 	- 프로그램과 외부매체(파일, 네트워크, 키보드, 콘솔...)간의 데이터 전달 통로(객체)
	 	- 데이터를 순차적으로 흘려보내는 파이프 구조.
	 	
	 	스트림의 특징
	 	1. 단방향 : 입력이면 입력만 출력이면 출력만 가능
	 	2. 순차적처리(FIFO) : 먼저보낸 데이터가 먼저 나온다.
	 	3. 시간 지연 가능성 : 외부매체와 연결되므로 입출력속도가 상대적으로 느릴 수 있음.
	 	
	 	스트림의 구분
	 	> 데이터 단위에 따른 구분 : 바이트스트림(1byte), 문자스트림(2byte)
	 	  - 바이트스트림(InputStream, OutputStream) : 이미지, 동영상, 바이너리 데이터등....
	 	  - 문자스트림(Reader, Writer) : 텍스트 데이터(문자/ 문자열)...
	 	  
	 	연결 방식에 따른 구분
	 	기반스트림 : 외부매체와 직접 연결되는 스트림(필수)
	 	보조스트림 : 기반스트림을 보조하는 역할로 기능을 추가제공하거나 성능향상을 도와주는 스트림(단독사용x)
	 */

	public static void main(String[] args) {
		ServeStream ss = new ServeStream();
//		ss.fileSave();
//		ss.fileRead();
//		ss.ObjectSave();
		ss.ObjectRead();
	}

}
