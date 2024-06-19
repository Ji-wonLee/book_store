package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface CartDao {

	List<CartDto> listCartItems(String user_id); // 장바구니 조회
	void updateCartItemAndTotal(CartDto cartDto); // 수량 및 총가격, 시간 업데이트
	void updateCartState(CartDto cartDto); // 장바구니 상태 업데이트
	void createNewCart(CartDto cartDto); // 새 장바구니 생성
	void addProductToCartDetails(CartDto cartDto); // 장바구니에 상품 추가
	int getStock(String product_id);// 재고확인

	
	String getLatestCartId();	// 최신 id 
    void insertNewCart(CartDto cartDto); // 새 장바구니 만들기

}