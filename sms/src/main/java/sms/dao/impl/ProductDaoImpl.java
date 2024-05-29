package sms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.ProductDao;
import sms.dto.Category;
import sms.dto.ProductDto;
import sms.dto.SearchDto;

@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	//Sql ����
	@Override
	public List<Category> categoryList( ){ // �з� ����Ʈ ���(�˻����� ���)
		
		List<Category> categoryList = sqlSessionTemplate.selectList(null); // mapper�� ��Ī.ȣ���� sql���� �̸�
		
		return categoryList;
	}
	
	@Override
	public List<ProductDto> productList() { // ��ǰ ����Ʈ(��ǰ ��ü ���)
		
		List<ProductDto> productList = sqlSessionTemplate.selectList(null);
		
		return productList;
	}

	@Override
	public List<ProductDto> ManagerproductList() { // ����� ����ϴ� ������ ��ǰ ��ȸ ����Ʈ
		
		List<ProductDto> managerProductList = sqlSessionTemplate.selectList(null);
		
		return managerProductList;
	}
	
	@Override
	public ProductDto productInfo(String ProductId) {
		
		ProductDto selectProduct = sqlSessionTemplate.selectOne(null, ProductId);
		
		return selectProduct;
	}

	@Override
	public List<ProductDto> productSearchWithText(String searchText) {
		
		List<ProductDto> searchTextList = sqlSessionTemplate.selectList(null, searchText);
		
		return searchTextList;
	}

	@Override
	public List<ProductDto> productSearchWithCategory(String categoryId) {
		
		List<ProductDto> searchCategoryList = sqlSessionTemplate.selectList(null, categoryId);
		
		return searchCategoryList;
	}

	@Override
	public List<ProductDto> productSearchDual(SearchDto searchDto) {
		
		List<ProductDto> searchDualList = sqlSessionTemplate.selectList(null, searchDto);
		
		return searchDualList;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
