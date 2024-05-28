package sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dto.ProductDto;
import sms.service.CartService;
import sms.dto.CartDto;
import sms.dto.PaymentDto;

public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;
	//
	//    @Transactional
	//    @Override
	//    public void proceedToCheckout(String userId) {
	//        // 결제 창으로 넘어갈 때 상태를 '결제중'으로 업데이트
	//        cartDao.updateCartState(userId, "결제중");
	//    }
	//
	//    @Override
	//    public void completePayment(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount) {
	//        // 필요하다면 PaymentInfo 객체를 생성
	//        PaymentDto paymentDto = new PaymentDto(userId, recipientName, recipientAddress, payerName, payerAccount);
	//        cartDao.savePaymentInfo(paymentDto);
	//        cartDao.updateCartStateToCompleted(userId);
	//    }
	//
	//    @Override
	//    public void addProductToCart(String userId, String productId, int quantity) {
	//        // 상품을 장바구니에 추가
	//        cartDao.addProductToCart(userId, productId, quantity);
	//    }
	//
	//    @Override
	//    public void savePaymentInfo(PaymentDto paymentInfo) {
	//        // 결제 정보 저장
	//        cartDao.savePaymentInfo(paymentInfo);
	//    }
	//
	//    @Override
	//    public String createPaymentRecord(String cartId) {
	//        // 새 결제 레코드 생성
	//        return cartDao.createPaymentRecord(cartId);
	//    }
	//
	//    @Override
	//    public String createNewCart(String userId) {
	//        // 새 장바구니 생성
	//        return cartDao.createNewCart(userId);
	//    }

	@Override
	public List<ProductDto> listCartItems(String userId) {
		// 사용자의 장바구니에 있는 모든 아이템을 조회
		return cartDao.listCartItems(userId);
	}

	@Override
	public void addProductToCart(String userId, String productId, int quantity) {
		// 상품을 장바구니에 추가
		cartDao.addProductToCart(userId, productId, quantity);
	}

	@Override
	public void proceedToCheckout(String userId) {
		// 결제 페이지로 진행하기 전에 장바구니 상태를 '결제중'으로 업데이트
		cartDao.updateCartState(userId, "결제중");
	}

	@Transactional
	@Override
	public void completePayment(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount) {
		// 결제 정보를 객체로 생성
		PaymentDto paymentDto = new PaymentDto(userId, recipientName, recipientAddress, payerName, payerAccount);
		// 결제 정보 저장
		cartDao.savePaymentInfo(paymentDto);
		// 결제 상태를 '결제완료'로 변경
		cartDao.updateCartStateToCompleted(userId);
	}
}