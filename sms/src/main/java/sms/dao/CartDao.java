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
	int getStock(String product_id);// ���Ȯ��

	
	String getLatestCartId();	// �ֽ� id 
    void insertNewCart(CartDto cartDto); // �� ��ٱ��� �����

}