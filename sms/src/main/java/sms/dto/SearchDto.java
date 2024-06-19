package sms.dto;

public class SearchDto {
	
	private String searchText; 
	private String category_id;
	
	public SearchDto() {}

	public SearchDto(String searchText, String categoryId) {
		this.searchText = searchText;
		category_id = categoryId;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
}