package sms.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.Date;


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
		// Ư�� ������� ��ٱ��� �׸��� ��ȸ
		return cartDao.listCartItems(user_id);
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

//	@Override
//	@Transactional
//	public void createNewCart(CartDto cartResetDto) {
//		// �� ��ٱ��� ����
//		String newCartId = generateCartId();
//		cartResetDto.setCart_id(newCartId);
//		cartDao.createNewCart(cartResetDto);
//	}

	@Override
	@Transactional
	public void addProductToCartDetails(CartDto cartItemUpdateDto) {
		// ��ٱ��Ͽ� ��ǰ �߰�
		cartDao.addProductToCartDetails(cartItemUpdateDto);
	}

	@Override
	public int getStock(String productId) {
		//��� Ȯ��
		return cartDao.getStock(productId);
	}


	@Override
	public String createNewCart(String userId) {
		// �ֽ� cart_id ��������
		String latestCartId = cartDao.getLatestCartId();
		// �� cart_id ����
		String newCartId = generateNewCartId(latestCartId);
		// ������ �Ϸù�ȣ�� ����Ͽ� payment_id ����
		String paymentId = generateNewPaymentId(newCartId);

		// ������ cart_id�� payment_id�� �̿��Ͽ� CartDto ��ü ����
		CartDto newCart = new CartDto(newCartId, userId, "��ٱ���", new java.sql.Date(System.currentTimeMillis()));

		// �����ͺ��̽��� ���ο� ��ٱ��� ���� ����
		cartDao.insertNewCart(newCart);

		// ������ payment_id ��ȯ (�Ǵ� �ʿ信 ���� ������ cart_id ��ȯ ����)
		return newCartId;
	}

	private String generateNewCartId(String latestCartId) {
		// ���� ���� (latestCartId���� ���ڸ� �����ϰ� 1�� ����)
		int lastNumber = Integer.parseInt(latestCartId.substring(5)) + 1;
		return "CART-" + String.format("%04d", lastNumber);
	}

	private String generateNewPaymentId(String newCartId) {
		// ���� ���� (���� ��¥�� newCartId���� ������ ���� ���)
		String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
		return "SO" + dateString + "-" + newCartId.substring(5);
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}


}