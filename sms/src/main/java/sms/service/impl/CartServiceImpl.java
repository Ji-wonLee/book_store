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
import sms.dto.PaymentDto;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	PaymentDao paymentDao;

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


	//	/**
	//	 * paymentController�� �����..... ���߿� �ð��� ���´ٸ� �ű���...
	//	 */
	//	@Override
	//	@Transactional
	//	public void completePaymentAndCreateNewCart(PaymentDto paymentDto) {
	//		
	//		//�������� ����
	//				paymentDao.savePaymentInfo(paymentDto);
	//		
	//		// �ֽ� cart_id ��������
	//		String latestCartId = cartDao.getLatestCartId();
	//		
	//		// �� cart_id ����
	//		String newCartId = generateNewCartId(latestCartId);
	//		
	//		// ������ �Ϸù�ȣ�� ����Ͽ� payment_id ����
	//		String newPaymentId = generateNewPaymentId(newCartId);
	//
	//		
	//		// ���� ���� ����
	//		paymentDto.setPayment_id(newPaymentId);
	//		paymentDto.setCart_id(latestCartId);
	////		paymentDao.savePaymentInfo(paymentDto);
	//		
	//	//	PaymentDto savepayment = new PaymentDto(newPaymentId, latestCartId );
	//		
	//		
	//
	//		// ��ٱ��� ���� ������Ʈ
	//		CartDto cartStatusUpdateDto = new CartDto();
	//		cartStatusUpdateDto.setCart_id(latestCartId);
	//		cartStatusUpdateDto.setUser_id(paymentDto.getUser_id());
	//		cartStatusUpdateDto.setState("�����Ϸ�");
	//		cartDao.updateCartState(cartStatusUpdateDto);
	//
	//		// �� ��ٱ��� ����
	//		CartDto newCart = new CartDto(newCartId, paymentDto.getUser_id(), "��ٱ���", new java.sql.Date(System.currentTimeMillis()));
	//		cartDao.insertNewCart(newCart);
	//	}
	//
	//
	//	private String generateNewCartId(String latestCartId) {
	//		// ���� ���� (latestCartId���� ���ڸ� �����ϰ� 1�� ����)
	//		int lastNumber = Integer.parseInt(latestCartId.substring(5)) + 1;
	//		return "CART-" + String.format("%04d", lastNumber);
	//	}
	//
	//	private String generateNewPaymentId(String newCartId) {
	//		// ���� ���� (���� ��¥�� newCartId���� ������ ���� ���)
	//		String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
	//		return "SO" + dateString + "-" + newCartId.substring(5);
	//	}

    @Override
    @Transactional
    public void completePaymentAndCreateNewCart(PaymentDto paymentDto) {
        // ���� cart_id ���
        String existingCartId = paymentDto.getCart_id();

        // �ֽ� cart_id ��������
        String latestCartId = cartDao.getLatestCartId();

        // �� cart_id ����
        String newCartId = generateNewCartId(latestCartId);

        // ������ �Ϸù�ȣ�� ����Ͽ� payment_id ����
        String newPaymentId = generateNewPaymentId(latestCartId);

        // ���� ���� ���� ����
        paymentDao.savePaymentInfo(paymentDto);

        // ���� ��ٱ��� ���� ������Ʈ
        CartDto cartStatusUpdateDto = new CartDto();
        cartStatusUpdateDto.setCart_id(existingCartId);
        cartStatusUpdateDto.setUser_id(paymentDto.getUser_id());
        cartStatusUpdateDto.setState("�����Ϸ�");
        cartDao.updateCartState(cartStatusUpdateDto);

        // ���ο� ��ٱ��� ����
        CartDto newCart = new CartDto(newCartId, paymentDto.getUser_id(), "��ٱ���", new java.sql.Date(System.currentTimeMillis()));
        cartDao.insertNewCart(newCart);

        // ���ο� ���� ���� ����
        PaymentDto newPayment = new PaymentDto();
        newPayment.setPayment_id(newPaymentId);
        newPayment.setCart_id(newCartId);
        newPayment.setUser_id(paymentDto.getUser_id());
        paymentDao.saveNewPaymentInfo(newPayment);
    }

    private String generateNewCartId(String latestCartId) {
        int lastNumber = Integer.parseInt(latestCartId.substring(5)) + 1;
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