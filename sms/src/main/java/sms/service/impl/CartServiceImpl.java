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
	//        // ���� â���� �Ѿ �� ���¸� '������'���� ������Ʈ
	//        cartDao.updateCartState(userId, "������");
	//    }
	//
	//    @Override
	//    public void completePayment(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount) {
	//        // �ʿ��ϴٸ� PaymentInfo ��ü�� ����
	//        PaymentDto paymentDto = new PaymentDto(userId, recipientName, recipientAddress, payerName, payerAccount);
	//        cartDao.savePaymentInfo(paymentDto);
	//        cartDao.updateCartStateToCompleted(userId);
	//    }
	//
	//    @Override
	//    public void addProductToCart(String userId, String productId, int quantity) {
	//        // ��ǰ�� ��ٱ��Ͽ� �߰�
	//        cartDao.addProductToCart(userId, productId, quantity);
	//    }
	//
	//    @Override
	//    public void savePaymentInfo(PaymentDto paymentInfo) {
	//        // ���� ���� ����
	//        cartDao.savePaymentInfo(paymentInfo);
	//    }
	//
	//    @Override
	//    public String createPaymentRecord(String cartId) {
	//        // �� ���� ���ڵ� ����
	//        return cartDao.createPaymentRecord(cartId);
	//    }
	//
	//    @Override
	//    public String createNewCart(String userId) {
	//        // �� ��ٱ��� ����
	//        return cartDao.createNewCart(userId);
	//    }

	@Override
	public List<ProductDto> listCartItems(String userId) {
		// ������� ��ٱ��Ͽ� �ִ� ��� �������� ��ȸ
		return cartDao.listCartItems(userId);
	}

	@Override
	public void addProductToCart(String userId, String productId, int quantity) {
		// ��ǰ�� ��ٱ��Ͽ� �߰�
		cartDao.addProductToCart(userId, productId, quantity);
	}

	@Override
	public void proceedToCheckout(String userId) {
		// ���� �������� �����ϱ� ���� ��ٱ��� ���¸� '������'���� ������Ʈ
		cartDao.updateCartState(userId, "������");
	}

	@Transactional
	@Override
	public void completePayment(String userId, String recipientName, String recipientAddress, String payerName, String payerAccount) {
		// ���� ������ ��ü�� ����
		PaymentDto paymentDto = new PaymentDto(userId, recipientName, recipientAddress, payerName, payerAccount);
		// ���� ���� ����
		cartDao.savePaymentInfo(paymentDto);
		// ���� ���¸� '�����Ϸ�'�� ����
		cartDao.updateCartStateToCompleted(userId);
	}
}