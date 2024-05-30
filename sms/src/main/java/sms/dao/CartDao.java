package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.PaymentDto;

public interface CartDao {

	List<CartDto> listCartItems(String user_id); // ��ٱ��� ��ȸ
	void updateCartItemAndTotal(CartDto cartDto); // ���� �� �Ѱ���, �ð� ������Ʈ
	void updateCartState(CartDto cartDto); // ��ٱ��� ���� ������Ʈ
	void createNewCart(CartDto cartDto); // �� ��ٱ��� ����
	void addProductToCartDetails(CartDto cartDto); // ��ٱ��Ͽ� ��ǰ �߰�
	int getStock(String productId);// ���Ȯ��
	//	void addProductToCart(String userId, String productId, int quantity); // ��ǰ ��ٱ��Ͽ� �߰�
	//	void revertToCart(String userId, String cartId); // ������ -> ��ٱ���
	//	void updateCartStateToCompleted(String userId); // ���� ���� �Ϸ�� ������Ʈ
	//	String createNewCart(String userId); // �� ��ٱ��� ����
	//	String createPaymentRecord(String cartId); // ���� ��� ����
	//	void savePaymentInfo(PaymentDto paymentDto); // ���� ���� ����

}
