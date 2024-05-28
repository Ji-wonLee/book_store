package sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.ProductDao;
import sms.dto.ProductDto;
import sms.dto.SearchDto;
import sms.service.ProductSvc;

@Service
public class ProductSvcImpl implements ProductSvc{
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<ProductDto> productList() {
		List<ProductDto> productList = productDao.productList();
		return productList;
	}

	@Override
	public List<ProductDto> ManagerproductList() {
		List<ProductDto> managerProductList =  productDao.ManagerproductList();
		return managerProductList;
	}

	@Override
	public ProductDto productInfo(String ProductId) {
		ProductDto selectProduct = productDao.productInfo(ProductId);
		return selectProduct;
	}

	@Override
	public List<ProductDto> productSearchWithText(String searchText) {
		List<ProductDto> searchTextList = productDao.productSearchWithText(searchText);
		return searchTextList;
	}

	@Override
	public List<ProductDto> productSearchWithCategory(String categoryId) {
		List<ProductDto> searchCategoryList = productDao.productSearchWithCategory(categoryId);
		return searchCategoryList;
	}

	@Override
	public List<ProductDto> productSearchDual(SearchDto searchDto) {
		List<ProductDto> searchDualList = productDao.productSearchDual(searchDto);
		return searchDualList;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
