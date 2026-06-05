package com.kh.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Run {
	/*
	 	네트워크 : 여러대의 컴퓨터들이 연결되어있는 통신망
	 	
	 	IP주소 : 네트워크상에 각 컴퓨터를 식별하기위한 주소
	 	port : 컴퓨터내의 프로그램을 식별하기위한 코드
	 	
	 	서버 - 클라이언트 연결 방식
	 	클라이언트 : 서버에 요청을 보내는 컴퓨터
	 	서버 : 클라이언트의 요청을 받아서 고객에게 서비스를 제공해주는 컴퓨터 또는 프로그램
	 	
	 	도메인 : 특정 서버에 접근하기위한 정보(ip, port)를 사람이 읽고 기억하기 어렵기 때문에 쉽게 만들어 둔 주소
	 	        -> 실제 도메인으로 요청시 DNS(Domain Name System)에서 IP로 변경해서 위치를 찾음.
	 */

	//InetAddress : 네트워크 정보를 확인할 수 있는 객체
	//localhost : 내컴퓨터의 IP주소
	public static void main(String[] args) {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println(localhost); //내 PC명 + 내 IP주소(내부아이피)
			
			System.out.println("내 PC명 : " + localhost.getHostName());
			System.out.println("내 IP주소 : " + localhost.getHostAddress());
			
			System.out.println("==================================");
			
			InetAddress googleHost = InetAddress.getByName("www.google.co.kr");
			
			System.out.println("구글 PC명 : " + googleHost.getHostName());
			System.out.println("구글 IP주소 : " + googleHost.getHostAddress());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
