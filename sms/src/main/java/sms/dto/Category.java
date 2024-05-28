package sms.dto;

public class Category {
	// 검색을 위한 카테고리 Entity
	private String id;
	private String name;
	
	public Category() {
		
	}
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}