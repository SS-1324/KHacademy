package com.kh.interface1;

public interface Animal {
	//추상메서드
	//interface에서는 어차피 추상메서드만 사용이 가능하기 떄문에
	//기본적으로 abstract키워드를 사용하지 않아도 abstract(추상)메서드로 생성이 된다.
	/*public abstract*/ void speak();
	public abstract void move();
}
