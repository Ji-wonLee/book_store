package sms.dao;

import java.util.List;

import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface ProductDao {
	List<ProductDto> productList();
	// 사용자에게 제공되는 객체 List를 받아옴
	
	List<ProductDto> ManagerproductList();
	// 관리자에게 제공되는 객체 List를 받아옴
	
	ProductDto productInfo(String ProductId);
	// id값으로 객체를 받아옴, 상세 조회에서 사용
	
	List<ProductDto> productSearchWithText(String searchText);
	// 입력창에 글자를 검색해서 객체 list를 찾아옴, 해당 문자를 객체ID 또는 객체 명과 비교
	
	List<ProductDto> productSearchWithCategory(String categoryId);
	// 분류를 검색하여 객체 list를 찾아옴
	
	List<ProductDto> productSearchDual(SearchDto searchDto);
	// 분류와 글자를 입력 받아서 객체 리스트를 찾아옴, 문자를 ID, 객체 명과 비교
} 