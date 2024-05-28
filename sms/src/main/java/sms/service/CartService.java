package sms.service;

import java.util.List;

import sms.dto.PaymentDto;
import sms.dto.ProductDto;

public interface CartService {

	//	List<ProductDto> listCartItems(String userId); // 장바구니 항목 조회
	//
	//	void proceedToCheckout(String userId); // 결제중 상태로 업데이트
	//
	//	// 결제 완료 및 새 장바구니 생성을 처리하는 메소드
	//	void completePayment(String userId, PaymentDto paymentDto);
	//
	//	// 장바구니에 상품을 추가하는 메소드
	//	void addProductToCart(String userId, String productId, int quantity);
	//
	//	// 결제 정보를 저장하는 메소드
	//	void savePaymentInfo(PaymentDto paymentInfo);
	//
	//	// 결제 상태를 '결제중'에서 '결제완료'로 변경하는 메소드
	//	void updateCartStateToCompleted(String userId);
	//
	//	// 새로운 결제 레코드를 생성하는 메소드
	//	String createPaymentRecord(String cartId);
	//
	//	// 새로운 장바구니를 생성하는 메소드
	//	String createNewCart(String userId);
	List<ProductDto> listCartItems(String userId); // 장바구니 항목 조회
	void addProductToCart(String userId, String productId, int quantity); // 상품 추가
	void proceedToCheckout(String userId); // 결제중 상태로 업데이트
	void completePayment(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount); // 결제 완료 처리
}
