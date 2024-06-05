package sms.dao;

import sms.dto.CartDto;

public interface SelectDao {
	public String findCartId(String user_id);
	// Controller의 Session에서 User_id를 받아 현재 사용중인 장바구니를 받아오는 Method
	// addCart 내부에서 호출하여 사용합니다.
	public int addCart(CartDto cartDto);
	// Cart_detail 에 객체를 추가하는 Method
	// 화면에서 product_id, quantity, price를 받아옵니다.
	// 이 경우 quantity는 객체의 수량을 의미합니다.
}