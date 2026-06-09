package com.kh.thread.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend extends Thread{
	private Socket socket;

	ServerSend(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Scanner sc = new Scanner(System.in);
				PrintWriter pw = new PrintWriter(socket.getOutputStream());){
			
			while(true) {
				System.out.print("cli로 보낼내용 : ");
				String sendMsg = sc.nextLine();
				
				pw.println(sendMsg);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
