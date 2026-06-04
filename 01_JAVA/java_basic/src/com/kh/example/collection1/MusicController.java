package com.kh.example.collection1;

import java.util.ArrayList;
import java.util.List;

public class MusicController {
	private List<Music> list = new ArrayList<>();
	
	// 마지막 위치에 곡 추가(성공 시 1)
	public int addList(Music music) {
		return list.add(music) ? 1 : 0;
	}
	
	// 첫 위치(인덱스 0)에 곡 추가(성공 시 1)
	public int addAtZero(Music music) {
		if(music == null)
			return 0;
		
		list.add(0, music);
		return 1;
	}
	
	// 전체 목록 반환
	public List<Music> printAll(){
		return list;
	}
	
	//곡 명으로 단건 검색(없으면 null)
	public Music searchMusic(String title) {
		for(Music m : list) {
			if(m.getTitle().equals(title)) {
				return m;
			}
		}
		
		return null;
	}
	
	//곡 명으로 찾아 삭제 후 삭제된 객체 반환(없으면 null)
	public Music removeMusic(String title) {
		for(Music m : list) {
			if(m.getTitle().equals(title)) {
				Music removeMusic = m;
				list.remove(m);
				return removeMusic;
			}
		}
		
		return null;
	}
	
	//곡 명으로 찾아 삭제 후 삭제된 객체 반환(없으면 null)
	public Music setMusic(String title, Music music) {
		for(int i=0; i<list.size(); i++) {
			Music m = list.get(i);
			if(m.getTitle().equals(title)) {
				list.set(i, music);
				return m;
			}
		}
		
		return null;
	}
}
