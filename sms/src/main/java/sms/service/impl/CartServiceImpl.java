package sms.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dao.PaymentDao;
import sms.dto.ProductDto;
import sms.service.CartService;
import sms.dto.CartDto;
import sms.dto.Inventory;
import sms.dto.PaymentDto;
import sms.dto.ProductDto;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	PaymentDao paymentDao;

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
	public void updateCartState(CartDto cartDto) {
		// 장바구니 상태 업데이트
		cartDao.updateCartState(cartDto);
	}

	@Override
	public void updateStock(Inventory inventory) {
		// 재고 업데이트 
		cartDao.updateStock(inventory);
	}


	@Override
	public String findCartId(String user_id) {
		return cartDao.findCartId(user_id);
	}

	@Override
	public int addCart(CartDto cartDto) {
		return cartDao.addCart(cartDto);
	}



	@Override
	public int getStock(String productId) {
		//재고 확인
		return cartDao.getStock(productId);
	}


	@Override
	@Transactional
	public String createNewCart(CartDto cartDto) {
		String maxCartId = cartDao.getMaxCartId();
		String newCartId = "CART-0001";  // 기본 ID

		if (maxCartId != null) {
			newCartId = generateNewCartId(maxCartId);
		}

		cartDto.setCart_id(newCartId);
		cartDao.insertNewCart(cartDto);
		return newCartId;
	}
	@Override
	public void revertCartState(String cartId) {
		// 상태 되돌리기
		CartDto cartDto = new CartDto(cartId, "장바구니");
		cartDao.updateCartState(cartDto);
	}

	@Override
	@Transactional
	public void updateCartStateAndGeneratePaymentId(String cartId, String state) {
		CartDto cartDto = new CartDto(cartId, state);
		cartDao.updateCartState(cartDto);

		if ("결제중".equals(state)) {
			String paymentId = generateNewPaymentId(cartId);
			PaymentDto paymentDto = new PaymentDto(cartId, paymentId);
			paymentDao.savePaymentInfo(paymentDto);
		}
	}

	//상품 삭제
	@Override
	public int deleteCartItem(CartDto cartDto) {
		return cartDao.deleteCartItem(cartDto);
	}

	@Override
	public int updateCartItemQuantity(CartDto cartDto) {
		System.out.println("CartDto in service: " + cartDto);
		return cartDao.updateCartItemQuantity(cartDto);
	}

	private String generateNewCartId(String maxCartId) {
		int lastNumber = Integer.parseInt(maxCartId.substring(5)) + 1;
		return "CART-" + String.format("%04d", lastNumber);
	}
	private String generateNewPaymentId(String latestCartId) {
		String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
		return "SO" + dateString + "-" + latestCartId.substring(5);
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}


}