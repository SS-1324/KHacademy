package com.kh.thread.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReceive extends Thread{
	private Socket socket;

	ServerReceive(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		//클라이언트로부터 전달받은 메시지를 계속 출력
		try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			while(true) {
				String msg = br.readLine();
				System.out.println("cli : " + msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
