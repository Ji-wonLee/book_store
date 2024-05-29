package sms.dao;

import java.util.List;

import sms.dto.Inventory;
import sms.dto.ProductDto;

public interface InventoryDao {
	List<ProductDto> inventoryList();
	// ������ ��� ��ȸ ����Ʈ
	
	List<ProductDto> inventorySearchWithText(String searchText);
	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
	
//	List<ProductDto> inventorySearchWithCategory(String categoryId);
//	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
//	
//	List<ProductDto> inventorySearchDual(SearchDto searchDto);
//	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
	
	int iventoryUpdate(Inventory inventory);
	// �ش� ���̵��� ��� �����Ŵ
}