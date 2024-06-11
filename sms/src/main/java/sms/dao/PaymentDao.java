package sms.dao;

import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
import sms.dto.PaymentDto;

public interface PaymentDao {


	void savePaymentInfo(PaymentDto paymentDto);				//cart에서 넘어온 결제정보 리스트? 저장.
	void updateCartStateToCompleted(CartDto cartDto);			// 결제 상태를 ' 결제완료' 로
	void createPaymentRecord(PaymentDto paymentDto);			// 결제 기록 	
	 String createPaymentRecordAndNewCart(PaymentDto paymentDto);	//결제 기록 및 장바구니 정보.


	//String createNewCart(String userId);	
//	void saveNewPaymentInfo(PaymentDto paymentDto);  // 새로운 결제 정보 저장 메소드 추가
	
	 void savePaymentDetail(PaymentDetailDto paymentDetailDto);
	 
	 void updatePaymentInfo(PaymentDto paymentDto);
	 
	 boolean existsPaymentId(String paymentId);
}