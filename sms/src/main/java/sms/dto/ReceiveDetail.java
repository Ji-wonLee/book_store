package sms.dto;

public class ReceiveDetail {
	private String receive_id;
	private String product_id;
	private int quantity ;
	private int price;
	
	public ReceiveDetail() {}
	public ReceiveDetail(String receive_id, String product_id, int quantity, int price) {
		super();
		this.receive_id = receive_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ReceiveDetail [receive_id=" + receive_id + ", product_id=" + product_id + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
}
