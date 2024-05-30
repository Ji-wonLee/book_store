package sms.dao;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface PaymentDao {


	void savePaymentInfo(PaymentDto paymentDto);				//cart���� �Ѿ�� �������� ����Ʈ? ����.
	void updateCartStateToCompleted(CartDto cartDto);			// ���� ���¸� ' �����Ϸ�' ��
	void createPaymentRecord(PaymentDto paymentDto);			// ���� ��� 	
	void createPaymentRecordAndNewCart(PaymentDto paymentDto, String newCartId);	//���� ��� �� ��ٱ��� ����.


	//String createNewCart(String userId);	


}
