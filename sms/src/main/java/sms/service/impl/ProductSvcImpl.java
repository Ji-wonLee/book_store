package sms.service.impl;

import java.util.List;
import java.util.Map;

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
	public int selectProductCount() {
		return productDao.selectProductCount();
	}
	
	@Override
	public List<ProductDto> productList(Map<String, Object> param) {
		List<ProductDto> productList = productDao.productList(param);
		return productList;
	}

	@Override
	public ProductDto productInfo(String ProductId) {
		ProductDto selectProduct = productDao.productInfo(ProductId);
		return selectProduct;
	}
	
	@Override
	public List<ProductDto> productSearchList(SearchDto searchDto) {
	// �Է°��� �޾Ƽ� ��ǰ����� �˻��ϴ� ����� ����
		
		List<ProductDto> productSearchList;
		//�Է°��� List�� ����
		
		if(searchDto.getCategory_id().equals("all")) { // ī�װ� ���� ������� ��� = �Էµ� ���ڿ����� �̿��Ͽ� �˻�
			productSearchList = productDao.productSearchWithText(searchDto.getSearchText());
		} else if(searchDto.getSearchText().isEmpty() == true) { // �Էµ� ���ڿ��� ����ִ� ��� = ī�װ� ������ �̿��Ͽ� �˻�
			productSearchList = productDao.productSearchWithCategory(searchDto.getCategory_id());
		} else { // �� ���� �޾Ƽ� �˻�
			productSearchList = productDao.productSearchDual(searchDto);
		}
		return productSearchList;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}