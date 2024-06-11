package sms.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dao.PaymentDao;
import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
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
        String paymentId = generatePaymentId(paymentDto.getCart_id());
        paymentDto.setPayment_id(paymentId); // 결제정보 저장
        paymentDao.savePaymentInfo(paymentDto);
    }

    @Override
    @Transactional
    public void updateCartStateToCompleted(CartDto cartDto) {
        // 결제 상태 변경 (결제중 -> 결제완료)
        paymentDao.updateCartStateToCompleted(cartDto);
    }

	@Override
	@Transactional
	public void createPaymentRecord(PaymentDto paymentDto) {
		// 결제 기록 생성
		String paymentId = generatePaymentId(paymentDto.getCart_id());
		paymentDto.setPayment_id(paymentId);
		paymentDao.createPaymentRecord(paymentDto);
	}

    @Override
    @Transactional
    public void createPaymentRecordAndNewCart(PaymentDto paymentDto) {
        // 결제 기록 생성
        String paymentId = generatePaymentId(paymentDto.getCart_id());
        paymentDto.setPayment_id(paymentId);

        String newCartId = paymentDao.createPaymentRecordAndNewCart(paymentDto);

        // 새로운 장바구니 생성
        CartDto cartResetDto = new CartDto();
        cartResetDto.setUser_id(paymentDto.getUser_id());
        cartResetDto.setCart_id(newCartId);
        cartResetDto.setState("장바구니");
        cartResetDto.setCart_Date(new java.sql.Date(new Date().getTime()));

        cartDao.createNewCart(cartResetDto);
    }


    @Override
    public String generatePaymentId(String cartId) {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String serialPart = cartId.split("-")[1];
        String paymentId = "SO" + datePart + "-" + serialPart;
        
        // 중복된 payment_id가 존재하지 않도록 확인
        while (paymentDao.existsPaymentId(paymentId)) {
            int serialNumber = Integer.parseInt(serialPart);
            serialNumber++;
            serialPart = String.format("%04d", serialNumber);
            paymentId = "SO" + datePart + "-" + serialPart;
        }

        return paymentId;
    }
    @Override
    public boolean existsPaymentId(String paymentId) {
        return paymentDao.existsPaymentId(paymentId);
    }
    @Override
    public void savePaymentDetail(PaymentDetailDto paymentDetailDto) {
        paymentDao.savePaymentDetail(paymentDetailDto);
    }

    

    @Override
    public void updatePaymentInfo(PaymentDto paymentDto) {
        paymentDao.updatePaymentInfo(paymentDto);
    }
    
    public String generateCartId() {
        return cartDao.getLatestCartId();
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