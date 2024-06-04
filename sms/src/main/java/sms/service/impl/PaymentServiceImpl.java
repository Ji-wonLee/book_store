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
		// 결제 정보 저장
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);
		paymentDao.savePaymentInfo(paymentDto);
	}

	@Override
	@Transactional
	public void updateCartStateToCompleted(CartDto cartStatusUpdateDto) {
		// 결제 상태 변경 (결제중 -> 결제완료)
		paymentDao.updateCartStateToCompleted(cartStatusUpdateDto);
	}

	@Override
	@Transactional
	public void createPaymentRecord(PaymentDto paymentDto) {
		// 결제 기록 생성
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);
		paymentDao.createPaymentRecord(paymentDto);
	}

	@Override
	@Transactional
	public void createPaymentRecordAndNewCart(PaymentDto paymentDto) {
		// 결제 기록 생성
		String paymentId = generatePaymentId();
		paymentDto.setPayment_id(paymentId);

		// 새로운 장바구니 생성
		String newCartId = generateCartId();
		CartDto cartResetDto = new CartDto();
		cartResetDto.setUser_id(paymentDto.getUser_id());
		cartResetDto.setCart_id(newCartId);

		paymentDao.createPaymentRecordAndNewCart(paymentDto, newCartId);
		cartDao.createNewCart(cartResetDto);
	}

	private String generatePaymentId() {
		// payment_id는 SOyyyymmdd-로 시작하고 일련번호를 사용합니다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new java.util.Date());
		return "SO" + date + "-" + UUID.randomUUID().toString();
	}

	private String generateCartId() {
		// cart_id는 CART-로 시작하고 일련번호를 사용합니다.
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