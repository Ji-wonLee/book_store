package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface CartDao {

	 List<ProductDto> listCartItems(String userId); // ��ٱ��� ��ȸ
	    void updateCartItemAndTotal(String cartId, String productId, int quantity); // ���� �� �Ѱ��� ������Ʈ
	    void addProductToCart(String userId, String productId, int quantity); // ��ǰ ��ٱ��Ͽ� �߰�
	    void updateCartState(String userId, String state); // ��ٱ��� ���� ������Ʈ
	    void updateCartStateToCompleted(String userId); // ���� ���� �Ϸ�� ������Ʈ
	    String createNewCart(String userId); // �� ��ٱ��� ����
	    String createPaymentRecord(Integer cartId); // ���� ��� ����
	    void savePaymentInfo(PaymentDto paymentDto); // ���� ���� ����
	    void addProductToCartDetails(String cartId, String productId, int quantity); // ��ٱ��� �� ������ ��ǰ �߰�
}
