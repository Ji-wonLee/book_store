package sms.service;

import java.util.List;

import sms.dto.CartDto;
import sms.dto.Inventory;
import sms.dto.PaymentDto;
import sms.dto.ProductDto;

public interface CartService {


	List<CartDto> listCartItems(String user_id); 	// ��ٱ��� �׸� ��ȸ
	void updateCartItemAndTotal(CartDto cartDto);	// ����, �Ѿ�, �ð� ������Ʈ
	void updateCartState(CartDto cartDto); 	// īƮ ���� ������Ʈ
	 void updateStock(Inventory inventory);	// ��� ������Ʈ

	int getStock(String productId);		//���Ȯ��

	String createNewCart(CartDto cartDto); // �� īƮ ����

	void revertCartState(String cartId);	//���� �ǵ�����

	public String findCartId(String user_id);
	// Controller�� Session���� User_id�� �޾� ���� ������� ��ٱ��ϸ� �޾ƿ��� Method
	// addCart ���ο��� ȣ���Ͽ� ����մϴ�.
	public int addCart(CartDto cartDto);
	// Cart_detail �� ��ü�� �߰��ϴ� Method
	// ȭ�鿡�� product_id, quantity, price�� �޾ƿɴϴ�.
	// �� ��� quantity�� ��ü�� ������ �ǹ��մϴ�.
	
	void updateCartStateAndGeneratePaymentId(String cartId, String state);
}