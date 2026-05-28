package com.kh.example.oop7;

public class ProductController {
	//Product참조변수 10개 생성
	private Product[] pro = new Product[10];

	public ProductController() {
		pro[0] = new Product("갤럭시", 1200000, "삼성");
		pro[1] = new Product("아이폰", 1300000, "애플");
		pro[2] = new Product("아이패드", 800000, "애플");
	}
	
	public void insertProduct(String pName, int price, String brand) {
		//배열에 새로운 객체를 추가하려면
		//먼저 빈공간(주소값이 x, null)을 찾아야 한다.
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) { //배열의 i번째 요소가 비어있다.
				pro[i] = new Product(pName, price, brand);
				break; //빈 배열을 찾아서 객체를 생성했기 때문에 반복 종료
			}
		}
	}
	
	public Product[] selectProduct() {
		return pro;
	}
}
