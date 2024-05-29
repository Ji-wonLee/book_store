package sms.dto;

public class OrderDetail {
	private String order_id;
	private String product_id;
	private int quantity;
	private int price;
	
	public OrderDetail() {};
	public OrderDetail(String order_id, String product_id, int quantity, int price) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	
	
}
