package com.kh.example.collection1;

import java.util.Objects;

public class Music {
	private String title;
	private String singer;
	
	Music() {
		super();
	}
	Music(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}
	
	public String getTitle() {
		return title;
	}
	public String getSinger() {
		return singer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	@Override
	public String toString() {
		return singer + " - " + title;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Music) {
			Music m = (Music)obj;
			return this.title.equals(m.getTitle()) && this.singer.equals(m.getSinger());
		}
		
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		//Objects.hash(비교한 필드값1, 2...)
		//비교값을 통한 해시코드를 만들어 주는 메서드
		return Objects.hash(title, singer);
	}
	
}
