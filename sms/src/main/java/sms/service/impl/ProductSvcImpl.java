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
	// �Է°��� �޾Ƽ� ��ǰ����� �˻��ϴ� ����� ����
		
		List<ProductDto> productSearchList;
		//�Է°��� List�� ����
		
		if(searchDto.getCategory_id().isEmpty() == true) { // ī�װ� ���� ������� ��� = �Էµ� ���ڿ����� �̿��Ͽ� �˻�
			productSearchList = productDao.productSearchWithText(searchDto.getSearchText());
		} else if(searchDto.getSearchText().isEmpty() == true) { // �Էµ� ���ڿ��� ����ִ� ��� = ī�װ� ������ �̿��Ͽ� �˻�
			productSearchList = productDao.productSearchWithCategory(searchDto.getCategory_id());
		} else { // �� ���� �޾Ƽ� �˻�
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
	// ���� �ϳ��� �޼ҵ�(productSearchList)�� ��ħ

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
