package sms.service.impl;

import java.util.List;
import java.util.UUID;

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
	@Override
	public List<CartDto> listCartItems(String userId) {
		// Ư�� ������� ��ٱ��� �׸��� ��ȸ
		return cartDao.listCartItems(userId);
	}

	@Override
	@Transactional
	public void updateCartItemAndTotal(CartDto cartItemUpdateDto) {
		// ��ٱ��� �� ��ǰ�� ���� ������Ʈ �� �Ѿ� ����
		cartDao.updateCartItemAndTotal(cartItemUpdateDto);
	}

	@Override
	@Transactional
	public void updateCartState(CartDto cartStatusUpdateDto) {
		// ��ٱ��� ���� ������Ʈ
		cartDao.updateCartState(cartStatusUpdateDto);
	}

	@Override
	@Transactional
	public void createNewCart(CartDto cartResetDto) {
		// �� ��ٱ��� ����
		String newCartId = generateCartId();
		cartResetDto.setCart_id(newCartId);
		cartDao.createNewCart(cartResetDto);
	}

	@Override
	@Transactional
	public void addProductToCartDetails(CartDto cartItemUpdateDto) {
		// ��ٱ��Ͽ� ��ǰ �߰�
		cartDao.addProductToCartDetails(cartItemUpdateDto);
	}

	private String generateCartId() {
		// cart_id�� CART-�� �����ϰ� �Ϸù�ȣ�� ����մϴ�.
		return "CART-" + UUID.randomUUID().toString();
	}
}