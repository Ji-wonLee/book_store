package sms.service;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface PaymentService {
	void savePaymentInfo(PaymentDto paymentDto);		// �������� 
	void updateCartStateToCompleted(CartDto cartStatusUpdateDto);	// �����Ϸ�
	void createPaymentRecord(PaymentDto paymentDto);			// ���� ��� ����
	void createPaymentRecordAndNewCart(PaymentDto paymentDto);	// ������ϰ� ���ο� ��ٱ���

}