package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
import sms.dto.PaymentDto;

public interface PaymentService {
	void savePaymentInfo(PaymentDto paymentDto);		// �������� 
	void updateCartStateToCompleted(CartDto cartDto);	// �����Ϸ�
	void createPaymentRecord(PaymentDto paymentDto);			// ���� ��� ����
	void createPaymentRecordAndNewCart(PaymentDto paymentDto);	// ������ϰ� ���ο� ��ٱ���


	String generatePaymentId(String cartId);
	
	
	void savePaymentDetail(PaymentDetailDto paymentDetailDto);
	//String generatePaymentId(String cartId);
	
	void updatePaymentInfo(PaymentDto paymentDto); // �߰�
	
	boolean existsPaymentId(String paymentId);

	List<PaymentDetailDto> getPaymentDetails(String cartId); //payment���̺� ����Ʈ �����ֱ�
}