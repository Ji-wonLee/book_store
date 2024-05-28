package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface CartDao {

	 List<ProductDto> listCartItems(String userId); // 장바구니 조회
	    void updateCartItemAndTotal(String cartId, String productId, int quantity); // 수량 및 총가격 업데이트
	    void addProductToCart(String userId, String productId, int quantity); // 상품 장바구니에 추가
	    void updateCartState(String userId, String state); // 장바구니 상태 업데이트
	    void updateCartStateToCompleted(String userId); // 결제 상태 완료로 업데이트
	    String createNewCart(String userId); // 새 장바구니 생성
	    String createPaymentRecord(Integer cartId); // 결제 기록 생성
	    void savePaymentInfo(PaymentDto paymentDto); // 결제 정보 저장
	    void addProductToCartDetails(String cartId, String productId, int quantity); // 장바구니 상세 정보에 상품 추가
}
