package sms.dto;

public class SearchDto {
	
	private String searchText; 
	private String categoryId;
	
	public SearchDto() {}

	public SearchDto(String searchText, String categoryId) {
		this.searchText = searchText;
		this.categoryId = categoryId;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
