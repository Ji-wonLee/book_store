package sms.dto;

public class Inventory {
	private String product_id;
	private int quantity;
	
	public Inventory() {}
	
	public Inventory(String product_id, int quantity) {
		this.product_id = product_id;
		this.quantity = quantity;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getNumber() {
		return quantity;
	}

	public void setNumber(int quantity) {
		this.quantity = quantity;
	}
}