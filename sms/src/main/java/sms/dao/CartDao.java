package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.Inventory;
import sms.dto.PaymentDto;

public interface CartDao {

	List<CartDto> listCartItems(String user_id); // 장바구니 조회
	void updateCartItemAndTotal(CartDto cartDto); // 수량 및 총가격, 시간 업데이트
	void updateCartState(CartDto cartDto);	// 장바구니 상태 업데이트
	void updateStock(Inventory inventory);	// 장바구니 재고 업데이트 


	String createNewCart(CartDto cartDto); // 새 장바구니 생성

	int getStock(String product_id);// 재고확인


	String getLatestCartId();	// 최신 id 
	void insertNewCart(CartDto cartDto); // 새 장바구니 만들기


	public String findCartId(String user_id);
	// Controller의 Session에서 User_id를 받아 현재 사용중인 장바구니를 받아오는 Method
	// addCart 내부에서 호출하여 사용합니다.
	public int addCart(CartDto cartDto);
	// Cart_detail 에 객체를 추가하는 Method
	// 화면에서 product_id, quantity, price를 받아옵니다.
	// 이 경우 quantity는 객체의 수량을 의미합니다.

	String getMaxCartId();

	int deleteCartItem(CartDto cartDto);
	int updateCartItemQuantity(CartDto cartDto);
}