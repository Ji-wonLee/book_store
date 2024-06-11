package sms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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
	public List<Category> categoryList(){ // �з� ����Ʈ ���(�˻����� ���)
		
		List<Category> categoryList = sqlSessionTemplate.selectList("product.categoryList"); // mapper�� ��Ī.ȣ���� sql���� �̸�
		
		return categoryList;
	}
	
	@Override
	public int selectProductCount() { // ����ϴ� ��ü�� ��ü ������ �޾ƿ´�. �� ��쿡�� ��ǰ�� ��ü ����
		return sqlSessionTemplate.selectOne("product.productNumber");
	}
	
	@Override
	public List<ProductDto> productList(Map<String, Object> param) { // ��ǰ ����Ʈ(��ǰ ��ü ���), �ڵ��� ���뼺�� ���� Map<String, Object> �� ����Ѵ�.
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1) * numPerpage, numPerpage);
		List<ProductDto> productList = sqlSessionTemplate.selectList("product.productlist", null, rb);
		return productList;
	}

	@Override
	public ProductDto productInfo(String ProductId) { // ��ǰ ��
		
		ProductDto selectProduct = sqlSessionTemplate.selectOne("product.productInfo", ProductId);
		
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