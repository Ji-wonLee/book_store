package sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.ProductDao;
import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;
import sms.service.ProductSvc;

@Service
public class ProductSvcImpl implements ProductSvc{
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Category> categoryList() {
		List<Category> categoryList = productDao.categoryList();
		return categoryList;
	}
	
	@Override
	public List<ProductDto> productList() {
		List<ProductDto> productList = productDao.productList();
		return productList;
	}

	@Override
	public ProductDto productInfo(String product_id) {
		ProductDto selectProduct = productDao.productInfo(product_id);
		return selectProduct;
	}
	
	@Override
	public List<ProductDto> productSearchList(SearchDto searchDto) {
	// 입력값을 받아서 상품목록을 검색하는 기능을 수행
		
		List<ProductDto> productSearchList;
		//입력값을 List로 받음
		
		if(searchDto.getCategory_id().isEmpty() == true) { // 카테고리 값이 비어있을 경우 = 입력된 문자열만을 이용하여 검색
			productSearchList = productDao.productSearchWithText(searchDto.getSearchText());
		} else if(searchDto.getSearchText().isEmpty() == true) { // 입력된 문자열이 비어있는 경우 = 카테고리 값만을 이용하여 검색
			productSearchList = productDao.productSearchWithCategory(searchDto.getCategory_id());
		} else { // 두 값을 받아서 검색
			productSearchList = productDao.productSearchDual(searchDto);
		}
		
		return productSearchList;
	}

	/*
	 * @Override public List<ProductDto> productSearchWithText(String searchText) {
	 * List<ProductDto> searchTextList =
	 * productDao.productSearchWithText(searchText); return searchTextList; }
	 * 
	 * @Override public List<ProductDto> productSearchWithCategory(String
	 * categoryId) { List<ProductDto> searchCategoryList =
	 * productDao.productSearchWithCategory(categoryId); return searchCategoryList;
	 * }
	 * 
	 * @Override public List<ProductDto> productSearchDual(SearchDto searchDto) {
	 * List<ProductDto> searchDualList = productDao.productSearchDual(searchDto);
	 * return searchDualList;
	 *
	}*/
	// 위의 하나의 메소드(productSearchList)로 합침

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
