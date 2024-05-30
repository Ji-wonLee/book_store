package sms.dao;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface PaymentDao {


	void savePaymentInfo(PaymentDto paymentDto);				//cart에서 넘어온 결제정보 리스트? 저장.
	void updateCartStateToCompleted(CartDto cartDto);			// 결제 상태를 ' 결제완료' 로
	void createPaymentRecord(PaymentDto paymentDto);			// 결제 기록 	
	void createPaymentRecordAndNewCart(PaymentDto paymentDto, String newCartId);	//결제 기록 및 장바구니 정보.


	//String createNewCart(String userId);	


}
