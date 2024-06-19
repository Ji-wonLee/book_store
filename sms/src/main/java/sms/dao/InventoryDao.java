package sms.dao;

import java.util.List;
import java.util.Map;

import sms.dto.Inventory;
import sms.dto.ProductDto;

public interface InventoryDao {
	List<ProductDto> inventoryList(Map<String, Object> param);
	// 관리자 재고 조회 리스트
	int selectInventoryCount();
	// 전체 개수
	List<ProductDto> inventorySearchWithText(String searchText, Map<String, Object> param);
	int inventorySearchWithTextNum(String searchText);
	// 입력창에 글자를 검색해서 객체 list를 찾아옴, 해당 문자를 객체ID 또는 객체 명과 비교
//	List<ProductDto> inventorySearchWithCategory(String categoryId);
//	// 분류를 검색하여 객체 list를 찾아옴
//	List<ProductDto> inventorySearchDual(SearchDto searchDto);
//	// 분류와 글자를 입력 받아서 객체 리스트를 찾아옴, 문자를 ID, 객체 명과 비교
	
	int iventoryUpdate(Inventory inventory);
	// 해당 아이디의 재고를 변경시킴
}