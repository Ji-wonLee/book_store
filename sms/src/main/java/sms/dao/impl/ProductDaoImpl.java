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
	//Sql 연결
	@Override
	public List<Category> categoryList(){ // 분류 리스트 출력(검색에서 사용)
		
		List<Category> categoryList = sqlSessionTemplate.selectList("product.categoryList"); // mapper의 명칭.호출할 sql문의 이름
		
		return categoryList;
	}
	
	@Override
	public int selectProductCount() { // 출력하는 객체의 전체 개수를 받아온다. 이 경우에는 상품의 전체 개수
		return sqlSessionTemplate.selectOne("product.productNumber");
	}
	
	@Override
	public List<ProductDto> productList(Map<String, Object> param) { // 상품 리스트(상품 전체 출력), 코드의 재사용성을 위해 Map<String, Object> 을 사용한다.
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1) * numPerpage, numPerpage);
		List<ProductDto> productList = sqlSessionTemplate.selectList("product.productlist", null, rb);
		return productList;
	}

	@Override
	public ProductDto productInfo(String ProductId) { // 상품 상세
		
		ProductDto selectProduct = sqlSessionTemplate.selectOne("product.productInfo", ProductId);
		
		return selectProduct;
	}

	@Override
	public List<ProductDto> productSearchWithText(String searchText) { // 단어로 조회 
		
		List<ProductDto> searchTextList = sqlSessionTemplate.selectList("product.productSearchName", searchText);
		
		return searchTextList;
	}

	@Override
	public List<ProductDto> productSearchWithCategory(String categoryId) { // 분류로 조회
		
		List<ProductDto> searchCategoryList = sqlSessionTemplate.selectList("product.productSearchCategory", categoryId);
		
		return searchCategoryList;
	}

	@Override
	public List<ProductDto> productSearchDual(SearchDto searchDto) { // 둘 다 사용하여 조회
		
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