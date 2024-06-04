package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.dto.ProductDto;

public interface CartService {


	List<CartDto> listCartItems(String user_id); // ��ٱ��� �׸� ��ȸ
	void updateCartItemAndTotal(CartDto cartDto);// ����, �Ѿ�, �ð� ������Ʈ
	void updateCartState(CartDto cartDto);		// īƮ ���� ������Ʈ

	void addProductToCartDetails(CartDto cartDto);	//��ٱ��� ��ǰ�߰�
	int getStock(String productId);		//���Ȯ��
	
	 String createNewCart(String userId); //�� id �ο�
}