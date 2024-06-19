package sms.dao;

import sms.dto.CartDto;

public interface SelectDao {
	public String findCartId(String user_id);
	// Controller�� Session���� User_id�� �޾� ���� ������� ��ٱ��ϸ� �޾ƿ��� Method
	// addCart ���ο��� ȣ���Ͽ� ����մϴ�.
	public int addCart(CartDto cartDto);
	// Cart_detail �� ��ü�� �߰��ϴ� Method
	// ȭ�鿡�� product_id, quantity, price�� �޾ƿɴϴ�.
	// �� ��� quantity�� ��ü�� ������ �ǹ��մϴ�.
}