package sms.dao;

import java.util.List;

import sms.dto.ProductDto;

import sms.dto.CartDto;
import sms.dto.Inventory;
import sms.dto.PaymentDto;

public interface CartDao {

	List<CartDto> listCartItems(String user_id); // ��ٱ��� ��ȸ
	void updateCartItemAndTotal(CartDto cartDto); // ���� �� �Ѱ���, �ð� ������Ʈ
	void updateCartState(CartDto cartDto);	// ��ٱ��� ���� ������Ʈ
	void updateStock(Inventory inventory);	// ��ٱ��� ��� ������Ʈ 


	String createNewCart(CartDto cartDto); // �� ��ٱ��� ����

	int getStock(String product_id);// ���Ȯ��


	String getLatestCartId();	// �ֽ� id 
	void insertNewCart(CartDto cartDto); // �� ��ٱ��� �����


	public String findCartId(String user_id);
	// Controller�� Session���� User_id�� �޾� ���� ������� ��ٱ��ϸ� �޾ƿ��� Method
	// addCart ���ο��� ȣ���Ͽ� ����մϴ�.
	public int addCart(CartDto cartDto);
	// Cart_detail �� ��ü�� �߰��ϴ� Method
	// ȭ�鿡�� product_id, quantity, price�� �޾ƿɴϴ�.
	// �� ��� quantity�� ��ü�� ������ �ǹ��մϴ�.

	String getMaxCartId();

	int deleteCartItem(CartDto cartDto);
	int updateCartItemQuantity(CartDto cartDto);
}