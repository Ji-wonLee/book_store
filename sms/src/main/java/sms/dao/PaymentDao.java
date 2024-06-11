package sms.dao;

import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
import sms.dto.PaymentDto;

public interface PaymentDao {


	void savePaymentInfo(PaymentDto paymentDto);				//cart���� �Ѿ�� �������� ����Ʈ? ����.
	void updateCartStateToCompleted(CartDto cartDto);			// ���� ���¸� ' �����Ϸ�' ��
	void createPaymentRecord(PaymentDto paymentDto);			// ���� ��� 	
	 String createPaymentRecordAndNewCart(PaymentDto paymentDto);	//���� ��� �� ��ٱ��� ����.


	//String createNewCart(String userId);	
//	void saveNewPaymentInfo(PaymentDto paymentDto);  // ���ο� ���� ���� ���� �޼ҵ� �߰�
	
	 void savePaymentDetail(PaymentDetailDto paymentDetailDto);
	 
	 void updatePaymentInfo(PaymentDto paymentDto);
	 
	 boolean existsPaymentId(String paymentId);
}