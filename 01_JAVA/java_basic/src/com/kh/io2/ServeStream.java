package com.kh.io2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class ServeStream {
	
	//프로그램 -> 파일(출력)
	//안녕하세요. 반가워요. 끝.
	public void fileSave() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			//1. 기반스트림 생성 -> 파일로 2바이트씩 내보낼 수 있는 스트림
			fw = new FileWriter("1_buffer.txt");
			
			//2. 보조스트림 생성(기반스트림을 전달) -> 한줄씩 쓰기위한 버퍼공간을 할당. 
			bw = new BufferedWriter(fw);
			
			bw.write("안녕하세요.\n");
			bw.write("반갑습니다.");
			bw.newLine();
			bw.write("끝\n");
			bw.flush(); //강제로 다 내보냄.
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//파일 -> 프로그램
	public void fileRead() {
		//FileReader : 파일을 직접적으로 연결해서 2바이트단위로 입력받을 수 있는 기반스트림
		//BufferedReader : 한줄씩 읽어오는 기능이 추가된 보조스트림
		
		try (BufferedReader br = new BufferedReader(new FileReader("1_buffer.txt"));){
			/*
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine()); //파일의 모든 내용을 읽어오면 null이 반환
			*/
			
			String text = null;
			do {
				text = br.readLine();
				if(text == null)
					break;
				System.out.println(text);
			} while(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileSaveWithStream() {
		try {
			//1.기반스트림
			FileOutputStream fo = new FileOutputStream("2_buffer.txt");
			
			//2.OutputStream스트림기반의 Wirter 스트림 생성
			OutputStreamWriter osw = new OutputStreamWriter(fo, "UTF-8");
			
			//3. 보조스트림 -> 한줄씩 쓰기위한  
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("안녕하세요.\n");
			bw.write("반갑습니다.");
			bw.newLine();
			bw.write("끝\n");
			bw.flush(); //강제로 다 내보냄.
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 직렬화
	 객체는 메모리안에서 존재하는 인스턴스 개념이기 때문에 그대로 전송할 수 없음,
	 직렬화 후 이진데이터로 외부에 전송이 가능.
	 - 객체(Object)를 바이트형태로 변환하는 과정
	 - 파일에 저장하거나 네트워크로 전송할 수 있도록 함.
	 - 자바에서는 클래스를 직렬화하기 위해 직렬화 대상 클래스에게 implements Serializable 해야 함.
	 
	 역직렬화
	 저장해 두거나 전송했던 데이터를 다시 원래 객체로 사용하기위해 하는 행위.
	 - 저장된 바이트데이터를 다시 객체로 복원.
	 */
	
	//프로그램 -> 파일(출력)  : 객체 직렬화
	public void ObjectSave() {
		Product p1 = new Product("갤럭시 s26", 1000000, "삼성");
		Product p2 = new Product("갤럭시 s25", 900000, "삼성");
		Product p3 = new Product("아이폰 17", 1200000, "애플");
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("product1.txt"));){
			oos.writeObject(p1); //객체를 파일에 저장
			oos.writeObject(p2);
			oos.writeObject(p3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//파일 -> 프로그램 객체 입력 : 역직렬화
	public void ObjectRead() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("product1.txt"));){
			
			while(true) {
				Object obj = ois.readObject();
				if(obj instanceof Product) {
					Product p1 = (Product)obj;
					System.out.println(p1);
				}
			}
			
		} catch(EOFException e) {
			System.out.println("끝까지 출력 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
