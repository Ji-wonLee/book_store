package sms.dto;

public class OrderSearchDto {
	private String searchText; 
	private String category_id;
	private int remaining;
	
	public OrderSearchDto() {}
	public OrderSearchDto(String searchText, String category_id, int remaining) {
		this.searchText = searchText;
		this.category_id = category_id;
		this.remaining = remaining;
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
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	
	
}
