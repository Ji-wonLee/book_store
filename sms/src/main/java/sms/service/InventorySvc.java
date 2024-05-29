package sms.service;

import java.util.List;

import sms.dto.Inventory;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface InventorySvc {
	List<ProductDto> inventoryList();
	// 관리자 재고 조회 리스트
	
	List<ProductDto> inventorySearchList(SearchDto searchDto);
	
//	List<ProductDto> inventorySearchWithText(String searchText);
//	// 입력창에 글자를 검색해서 객체 list를 찾아옴, 해당 문자를 객체ID 또는 객체 명과 비교
//	List<ProductDto> inventorySearchWithCategory(String categoryId);
//	// 분류를 검색하여 객체 list를 찾아옴
//	List<ProductDto> inventorySearchDual(SearchDto searchDto);
//	// 분류와 글자를 입력 받아서 객체 리스트를 찾아옴, 문자를 ID, 객체 명과 비교
	
	int iventoryUpdate(Inventory inventory);
	// 해당 아이디의 재고를 변경시킴
}
