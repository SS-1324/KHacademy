package com.kh.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = null;
		BufferedReader br = null;
		
		//요청하고자하는 서버의 위치
		String serverIP = "192.168.10.41";
		int port = 3000;
		
		try {
			Socket socket = new Socket(serverIP, port);
		
			if(socket != null) {
				//입력용 스트림(클라이언트로부터 전달된 값을 읽기위함)
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
				//출력용 스트림
				pw = new PrintWriter(socket.getOutputStream());
				
				while(true) {
					System.out.print("보낼 내용 : ");
					String sendMessage = sc.nextLine();
					
					pw.println(sendMessage);
					pw.flush();
					
					String message = br.readLine();
					System.out.println("서버 : " + message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
