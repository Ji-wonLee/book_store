package sms.dto;

public class Inventory {
	private String product_id;
	private int number;
	
	public Inventory() {}
	
	public Inventory(String product_id, int number) {
		this.product_id = product_id;
		this.number = number;
	}

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
}