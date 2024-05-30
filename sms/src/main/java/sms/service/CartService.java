package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.dto.ProductDto;

public interface CartService {

	//	List<ProductDto> listCartItems(String userId); // ��ٱ��� �׸� ��ȸ
	//
	//	void proceedToCheckout(String userId); // ������ ���·� ������Ʈ
	//
	//	// ���� �Ϸ� �� �� ��ٱ��� ������ ó���ϴ� �޼ҵ�
	//	void completePayment(String userId, PaymentDto paymentDto);
	//
	//	// ��ٱ��Ͽ� ��ǰ�� �߰��ϴ� �޼ҵ�
	//	void addProductToCart(String userId, String productId, int quantity);
	//
	//	// ���� ������ �����ϴ� �޼ҵ�
	//	void savePaymentInfo(PaymentDto paymentInfo);
	//
	//	// ���� ���¸� '������'���� '�����Ϸ�'�� �����ϴ� �޼ҵ�
	//	void updateCartStateToCompleted(String userId);
	//
	//	// ���ο� ���� ���ڵ带 �����ϴ� �޼ҵ�
	//	String createPaymentRecord(String cartId);
	//
	//	// ���ο� ��ٱ��ϸ� �����ϴ� �޼ҵ�
	//	String createNewCart(String userId);
	List<CartDto> listCartItems(String user_id); // ��ٱ��� �׸� ��ȸ
	void updateCartItemAndTotal(CartDto cartDto);// ����, �Ѿ�, �ð� ������Ʈ
	void updateCartState(CartDto cartDto);		// īƮ ���� ������Ʈ
	void createNewCart(CartDto cartDto);	// �� ��ٱ��� ����
	void addProductToCartDetails(CartDto cartDto);	//��ٱ��� ��ǰ�߰�
	int getStock(String productId);		//���Ȯ��
}
