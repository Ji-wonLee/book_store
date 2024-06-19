package sms.dao;

import java.util.List;
import java.util.Map;

import sms.dto.Inventory;
import sms.dto.ProductDto;

public interface InventoryDao {
	List<ProductDto> inventoryList(Map<String, Object> param);
	// ������ ��� ��ȸ ����Ʈ
	int selectInventoryCount();
	// ��ü ����
	List<ProductDto> inventorySearchWithText(String searchText, Map<String, Object> param);
	int inventorySearchWithTextNum(String searchText);
	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
//	List<ProductDto> inventorySearchWithCategory(String categoryId);
//	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
//	List<ProductDto> inventorySearchDual(SearchDto searchDto);
//	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
	
	int iventoryUpdate(Inventory inventory);
	// �ش� ���̵��� ��� �����Ŵ
}