package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Inventory;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface InventorySvc {
	List<ProductDto> inventoryList(Map<String, Object> param);
	// ������ ��� ��ȸ ����Ʈ
	int inventoryListNum();
	
	List<ProductDto> inventorySearchWithText(String searchText, Map<String, Object> param);
	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
	int inventorySearchWithTextNum(String searchText);
	
	int iventoryUpdate(Inventory inventory);
	// �ش� ���̵��� ��� �����Ŵ
	
//	List<ProductDto> inventorySearchList(SearchDto searchDto);
//	List<ProductDto> inventorySearchWithCategory(String categoryId);
//	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
//	List<ProductDto> inventorySearchDual(SearchDto searchDto);
//	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
	
}