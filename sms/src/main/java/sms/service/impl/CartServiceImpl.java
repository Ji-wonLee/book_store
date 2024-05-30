package sms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dto.ProductDto;
import sms.service.CartService;
import sms.dto.CartDto;
import sms.dto.PaymentDto;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;
	@Override
	public List<CartDto> listCartItems(String user_id) {
		// 특정 사용자의 장바구니 항목을 조회
		return cartDao.listCartItems(user_id);
	}

	@Override
	@Transactional
	public void updateCartItemAndTotal(CartDto cartItemUpdateDto) {
		// 장바구니 내 상품의 수량 업데이트 및 총액 재계산
		cartDao.updateCartItemAndTotal(cartItemUpdateDto);
	}

	@Override
	@Transactional
	public void updateCartState(CartDto cartStatusUpdateDto) {
		// 장바구니 상태 업데이트
		cartDao.updateCartState(cartStatusUpdateDto);
	}

	@Override
	@Transactional
	public void createNewCart(CartDto cartResetDto) {
		// 새 장바구니 생성
		String newCartId = generateCartId();
		cartResetDto.setCart_id(newCartId);
		cartDao.createNewCart(cartResetDto);
	}

	@Override
	@Transactional
	public void addProductToCartDetails(CartDto cartItemUpdateDto) {
		// 장바구니에 상품 추가
		cartDao.addProductToCartDetails(cartItemUpdateDto);
	}

	private String generateCartId() {
		// cart_id는 CART-로 시작하고 일련번호를 사용합니다.
		return "CART-" + UUID.randomUUID().toString();
	}
}