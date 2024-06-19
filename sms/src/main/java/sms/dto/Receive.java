package sms.dto;

public class Receive {
	private String receive_id;
	private String order_id;
	private String receive_date;
	private String writer;
	private String payer;
	private int totalprice;
	
	public Receive() {}
	public Receive(String receive_id, String order_id, String receive_date, String writer, String payer,
			int totalprice) {
		super();
		this.receive_id = receive_id;
		this.order_id = order_id;
		this.receive_date = receive_date;
		this.writer = writer;
		this.payer = payer;
		this.totalprice = totalprice;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getReceive_date() {
		return receive_date;
	}
	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
	
}
