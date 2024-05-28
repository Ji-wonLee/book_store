package sms.dto;

import java.sql.Date;

public class CartDto {
	private String cartId;			// īƮ������ȣ
	private String userId;			// userId
	private int totalPrice;			// �� ����
	private Date cartDate;			// īƮ �������� �� ��������
	private String state;			// īƮ ����; ��ٱ���, �ǸſϷ�
	private String productId;		// ��ǰ id
	private int quantity;			// ����	
	private int price;			// �ܰ�


	public CartDto() {}


	public CartDto(String cartId, String userId, int totalPrice, Date cartDate, String state, String productId,
			int quantity, int price) {
		this.cartId = cartId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.cartDate = cartDate;
		this.state = state;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}


	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCartDate() {
		return cartDate;
	}
	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
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
