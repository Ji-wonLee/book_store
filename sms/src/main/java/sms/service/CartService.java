package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.dto.ProductDto;

public interface CartService {


	List<CartDto> listCartItems(String user_id); // 장바구니 항목 조회
	void updateCartItemAndTotal(CartDto cartDto);// 수량, 총액, 시간 업데이트
	void updateCartState(CartDto cartDto);		// 카트 상태 업데이트

	void addProductToCartDetails(CartDto cartDto);	//장바구니 상품추가
	int getStock(String productId);		//재고확인
	
	 String createNewCart(String userId); //새 id 부여
}