package sms.dto;

import java.sql.Date;

public class cartDTO {
    private String cartId;			// īƮ������ȣ
    private String userId;			
    private int totalPrice;
    private Date cartDate;			// īƮ �������� �� ��������
    private String state;			// īƮ ����; ��ٱ���, �ǸſϷ�
	
    
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
