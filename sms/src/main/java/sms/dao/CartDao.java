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
	int getStock(String productId);// 재고확인
	//	void addProductToCart(String userId, String productId, int quantity); // 상품 장바구니에 추가
	//	void revertToCart(String userId, String cartId); // 결제중 -> 장바구니
	//	void updateCartStateToCompleted(String userId); // 결제 상태 완료로 업데이트
	//	String createNewCart(String userId); // 새 장바구니 생성
	//	String createPaymentRecord(String cartId); // 결제 기록 생성
	//	void savePaymentInfo(PaymentDto paymentDto); // 결제 정보 저장

}
