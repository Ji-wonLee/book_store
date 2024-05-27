package sms.dto;

import java.sql.Date;

public class cartDTO {
    private String cartId;			// 카트고유번호
    private String userId;			
    private int totalPrice;
    private Date cartDate;			// 카트 생성일자 및 수정일자
    private String state;			// 카트 상태; 장바구니, 판매완료
	
    
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
