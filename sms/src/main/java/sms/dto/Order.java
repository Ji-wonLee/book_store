package sms.dto;

public class Order {
	private String order_id;
	private String order_date;
	private String writer;
	private int totalprice;
	public Order() {}
	public Order(String order_id, String order_date, String writer, int totalprice) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.writer = writer;
		this.totalprice = totalprice;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
	
}
