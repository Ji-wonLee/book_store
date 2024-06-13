package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

public interface ProductSvc {
	List<Category> categoryList();
	// 검색을 위한 카테고리 리스트
	
	int selectProductCount();
	// 상품의 개수 출력
	
	List<ProductDto> productList(Map<String, Object> param);
	// 사용자에게 제공되는 객체 List를 받아옴

	ProductDto productInfo(String ProductId);
	// id값으로 객체를 받아옴, 상세 조회에서 사용
	
	List<ProductDto> productSearchList(SearchDto searchDto, Map<String, Object> param);
	int productSearchListNum(SearchDto searchDto);
	// 분류와 글자를 입력 받아서 객체 리스트를 찾아옴, 문자를 ID, 객체 명과 비교
}