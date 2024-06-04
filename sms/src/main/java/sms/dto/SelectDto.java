// Select 객체들은 나중에 Cart에 합쳐서 진행(Dto, Dao, Mapper, Svc, Controller)

package sms.dto;

public class SelectDto {
	
	private String product_id; //상품 번호
	private int number; // 개수
	private String state; // 상품상태
	private int price;				// 단가
	private String product_name; //상품명
	
	public SelectDto() {}
	
	public SelectDto(String product_id, int number, String state, int price, String product_name) {
		this.product_id = product_id;
		this.number = number;
		this.state = state;
		this.price = price;
		this.product_name = product_name;
	} // 전체 속성을 받아오는 객체
		
	public SelectDto(String product_id, int price, String product_name) {
		this.product_id = product_id;
		this.price = price;
		this.product_name = product_name;
	} // Cart에 보내주는 객체

	public SelectDto(String product_id, int number) {
		this.product_id = product_id;
		this.number = number;
	} // info 에서 받아오는 객체

	public SelectDto(String product_id, int number, int price, String product_name) {
		this.product_id = product_id;
		this.number = number;
		this.price = price;
		this.product_name = product_name;
	} // Cart에 저장되는 객체 **통합

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}