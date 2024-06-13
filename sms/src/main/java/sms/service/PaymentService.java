package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
import sms.dto.PaymentDto;

public interface PaymentService {
	void savePaymentInfo(PaymentDto paymentDto);		// 결제정보 
	void updateCartStateToCompleted(CartDto cartDto);	// 결제완료
	void createPaymentRecord(PaymentDto paymentDto);			// 결제 기록 생성
	void createPaymentRecordAndNewCart(PaymentDto paymentDto);	// 결제기록과 새로운 장바구니


	String generatePaymentId(String cartId);
	
	
	void savePaymentDetail(PaymentDetailDto paymentDetailDto);
	//String generatePaymentId(String cartId);
	
	void updatePaymentInfo(PaymentDto paymentDto); // 추가
	
	boolean existsPaymentId(String paymentId);

	List<PaymentDetailDto> getPaymentDetails(String cartId); //payment테이블 리스트 보여주기
}