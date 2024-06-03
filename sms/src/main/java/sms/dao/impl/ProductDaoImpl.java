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
		
		List<Category> categoryList = sqlSessionTemplate.selectList("product.categoryList"); // mapper�� ��Ī.ȣ���� sql���� �̸�
		
		return categoryList;
	}
	
	@Override
	public List<ProductDto> productList() { // ��ǰ ����Ʈ(��ǰ ��ü ���)
		
		List<ProductDto> productList = sqlSessionTemplate.selectList("product.productlist");
		
		return productList;
	}

	@Override
	public ProductDto productInfo(String product_id) { // ��ǰ ��
		
		ProductDto selectProduct = sqlSessionTemplate.selectOne("product.productInfo", product_id);
		
		return selectProduct;
	}

	@Override
	public List<ProductDto> productSearchWithText(String searchText) { // �ܾ�� ��ȸ 
		
		List<ProductDto> searchTextList = sqlSessionTemplate.selectList("product.productSearchName", searchText);
		
		return searchTextList;
	}

	@Override
	public List<ProductDto> productSearchWithCategory(String categoryId) { // �з��� ��ȸ
		
		List<ProductDto> searchCategoryList = sqlSessionTemplate.selectList("product.productSearchCategory", categoryId);
		
		return searchCategoryList;
	}

	@Override
	public List<ProductDto> productSearchDual(SearchDto searchDto) { // �� �� ����Ͽ� ��ȸ
		
		List<ProductDto> searchDualList = sqlSessionTemplate.selectList("product.productSearchDual", searchDto);
		
		return searchDualList;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
