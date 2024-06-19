package sms.service;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface PaymentService {
	void savePaymentInfo(PaymentDto paymentDto);		// 결제정보 
	void updateCartStateToCompleted(CartDto cartStatusUpdateDto);	// 결제완료
	void createPaymentRecord(PaymentDto paymentDto);			// 결제 기록 생성
	void createPaymentRecordAndNewCart(PaymentDto paymentDto);	// 결제기록과 새로운 장바구니

}