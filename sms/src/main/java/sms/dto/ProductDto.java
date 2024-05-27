package sms.dto;

// 관리자 조회에서 사용하는 DTO
// 재고 조회, 입고, 출고 조회에서 사용할 예정
// pull request 에서 추가할 사항을 작성해 주세요

public class ProductDto {
	private String id; // 상품 id
	private String name; // 상품명
	private String description; // 상세설명
	private String price; // 단가 가격
	private String manufactureName; // 회사명
	private String manufactureAddress; // 회사 주소
	private String categoryId; // 분류 id
	private String categoryName; // 분류 이름
	private String imgurl; // 표지 url
	private int page; // 총 페이지 수 
	private String state; // 상태
	private int quantity; // 수량
	
	public ProductDto() {}

	// 전체 데이터 조회
	public ProductDto(String id, String name, String description, String price, String manufactureName,
			String manufactureAddress, String categoryId, String categoryName, String imgurl, int page, String state,
			int quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.manufactureName = manufactureName;
		this.manufactureAddress = manufactureAddress;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.imgurl = imgurl;
		this.page = page;
		this.state = state;
		this.quantity = quantity;
	}
	
	//관리자 전체 조회
	public ProductDto(String id, String name, String price, String manufactureName, String manufactureAddress,
			String categoryId, String categoryName, int page, String state, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.manufactureName = manufactureName;
		this.manufactureAddress = manufactureAddress;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.page = page;
		this.state = state;
		this.quantity = quantity;
	}

	//사용자 전체 조회
	public ProductDto(String name, String price, String categoryName, int page, String state) {
		this.name = name;
		this.price = price;
		this.categoryName = categoryName;
		this.page = page;
		this.state = state;
	}

	//사용자 상세 조회
	public ProductDto(String name, String description, String price, String manufactureName, String categoryName,
			String imgurl, int page, String state) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.manufactureName = manufactureName;
		this.categoryName = categoryName;
		this.imgurl = imgurl;
		this.page = page;
		this.state = state;
	}

	//변경 예정
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", manufactureName=" + manufactureName + ", manufactureAddress=" + manufactureAddress
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", imgurl=" + imgurl + ", page="
				+ page + ", state=" + state + ", quantity=" + quantity + "]";
	}
}