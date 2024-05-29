package sms.dto;

// 관리자 조회에서 사용하는 DTO
// 재고 조회, 입고, 출고 조회에서 사용할 예정
// pull request 에서 추가할 사항을 작성해 주세요

public class ProductDto {
	private String product_id; // 상품 id
	private String product_name; // 상품명
	private String description; // 상세설명
	private String product_price; // 단가 가격
	private String manufacture_name; // 회사명
	private String manufacture_address; // 회사 주소
	private String category_id; // 분류 id
	private String category_name; // 분류 이름
	private String product_imgurl; // 표지 url
	private int product_page; // 총 페이지 수 
	private String state; // 상태
	private int quantity; // 수량
	
	public ProductDto() {}

	// 전체 데이터 조회
	public ProductDto(String id, String name, String description, String price, String manufactureName,
			String manufactureAddress, String categoryId, String categoryName, String imgurl, int page, String state,
			int quantity) {
		product_id  = id;
		product_name = name;
		this.description = description;
		product_price = price;
		manufacture_name = manufactureName;
		manufacture_address = manufactureAddress;
		category_id = categoryId;
		category_name = categoryName;
		product_imgurl = imgurl;
		product_page = page;
		this.state = state;
		this.quantity = quantity;
	}
	
	//관리자 전체 조회
	public ProductDto(String id, String name, String price, String manufactureName, String manufactureAddress,
			String categoryId, String categoryName, int page, String state, int quantity) {
		product_id = id;
		product_name = name;
		product_price = price;
		manufacture_name = manufactureName;
		manufacture_address = manufactureAddress;
		category_id = categoryId;
		category_name = categoryName;
		product_page = page;
		this.state = state;
		this.quantity = quantity;
	}

	//사용자 전체 조회
	public ProductDto(String name, String price, String categoryName, int page, String state) {
		product_name = name;
		product_price = price;
		category_name = categoryName;
		product_page = page;
		this.state = state;
	}

	//사용자 상세 조회
	public ProductDto(String name, String description, String price, String manufactureName, String categoryName,
			String imgurl, int page, String state) {
			product_name = name;
		this.description = description;
		product_price = price;
		manufacture_name = manufactureName;
		category_name = categoryName;
		product_imgurl = imgurl;
		product_page = page;
		this.state = state;
	}
	
	//test_나예은
	public ProductDto(String id, String name, String price, String manufactureName, String categoryName, String imgurl,
			String state) {
		product_id = id;
		product_name = name;
		product_price = price;
		manufacture_name = manufactureName;
		category_name = categoryName;
		product_imgurl = imgurl;
		this.state = state;
	}
	

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	
	public String getManufacture_name() {
		return manufacture_name;
	}

	public void setManufacture_name(String manufacture_name) {
		this.manufacture_name = manufacture_name;
	}

	public String getManufacture_address() {
		return manufacture_address;
	}

	public void setManufacture_address(String manufacture_address) {
		this.manufacture_address = manufacture_address;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getProduct_imgurl() {
		return product_imgurl;
	}

	public void setProduct_imgurl(String product_imgurl) {
		this.product_imgurl = product_imgurl;
	}

	public int getProduct_page() {
		return product_page;
	}

	public void setProduct_page(int product_page) {
		this.product_page = product_page;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
