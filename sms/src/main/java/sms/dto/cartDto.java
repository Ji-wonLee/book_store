package sms.dto;

import java.sql.Date;

public class cartDto {
	private String cartId;			// īƮ������ȣ
	private String userId;			// userId
	private int totalPrice;			// �� ����
	private Date cartDate;			// īƮ �������� �� ��������
	private String state;			// īƮ ����; ��ٱ���, �ǸſϷ�


	public cartDto() {}
	
	public cartDto(String cartId, String userId, int totalPrice, Date cartDate, String state) {
		this.cartId = cartId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.cartDate = cartDate;
		this.state = state;
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




}
