package com.kh.example.poly2;

public class LibraryController {
	private Member mem = null;
	private Book[] bList = new Book[5];
	
	LibraryController() {
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("아기공룡 뿌꾸", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	
	public void insertMember(Member mem) {
		this.mem = mem;
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	//문자열1.contains(문자열2) : 문자열1에 문자열2가 포함되어있는지 여부 반환
	public Book[] searchBook(String keyword) {
		Book[] searchBook = new Book[bList.length];
		//[백,루,n,n,n]
		
		int count = 0;
		//bList에서 제목에 keyword이 포함된 것들만 반환
		for(Book b : bList) {
			if(b == null) {
				break;
			}
			
			if(b.getTitle() != null && b.getTitle().contains(keyword)) {
				searchBook[count] = b;
				count++;
			}
		}
		
		return searchBook;
	}
	
	public int rentBook(int index) {
		Book b = bList[index];
		
		if(b == null) {
			return 3; //index번째 책이 없음 실패.
		}
		
		if(b instanceof AniBook) {
			AniBook ab = (AniBook)b;
			if(mem.getAge() < ab.getAccessAge()) {
				return 1; //나이제한 실패
			}
		} else if(b instanceof CookBook) {
			CookBook cb = (CookBook)b;
			if(cb.isCoupon()) {
				mem.setCouponCount(mem.getCouponCount() + 1);
				return 2;//대여성공 + 쿠폰발급
			}
		}
		
		return 0; //대여성공
	}
}
