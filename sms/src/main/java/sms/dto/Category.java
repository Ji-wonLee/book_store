package sms.dto;

public class Category {
	// 검색을 위한 카테고리 Entity
	private String category_id;
	private String category_name;
	
	public Category() {
		
	}
	
	public Category(String id, String name) {
		category_id = id;
		category_name = name;
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
}