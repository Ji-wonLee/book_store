package sms.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dao.PaymentDao;
import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	CartDao cartDao;

	@Override
	@Transactional
	public void savePaymentInfo(PaymentDto paymentDto) {
		// ���� ���� ����
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);
		paymentDao.savePaymentInfo(paymentDto);
	}

	@Override
	@Transactional
	public void updateCartStateToCompleted(CartDto cartStatusUpdateDto) {
		// ���� ���� ���� (������ -> �����Ϸ�)
		paymentDao.updateCartStateToCompleted(cartStatusUpdateDto);
	}

	@Override
	@Transactional
	public void createPaymentRecord(PaymentDto paymentDto) {
		// ���� ��� ����
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);
		paymentDao.createPaymentRecord(paymentDto);
	}

	@Override
	@Transactional
	public void createPaymentRecordAndNewCart(PaymentDto paymentDto) {
		// ���� ��� ����
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);

		// ���ο� ��ٱ��� ����
		String newCartId = generateCartId();
		CartDto cartResetDto = new CartDto();
		cartResetDto.setUser_id(paymentDto.getUser_id());
		cartResetDto.setCart_id(newCartId);

		paymentDao.createPaymentRecordAndNewCart(paymentDto, newCartId);
		cartDao.createNewCart(cartResetDto);
	}

	private String generatePaymentId() {
		// payment_id�� SOyyyymmdd-�� �����ϰ� �Ϸù�ȣ�� ����մϴ�.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new java.util.Date());
		return "SO" + date + "-" + UUID.randomUUID().toString();
	}

	private String generateCartId() {
		// cart_id�� CART-�� �����ϰ� �Ϸù�ȣ�� ����մϴ�.
		return "CART-" + UUID.randomUUID().toString();
	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	
	
}