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
	public List<ProductDto> productSearchWithText(String searchText, Map<String, Object> param) { // �ܾ�� ��ȸ 
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1) * numPerpage, numPerpage);
		List<ProductDto> searchTextList = sqlSessionTemplate.selectList("product.productSearchName", searchText, rb);
		return searchTextList;
	}

	@Override
	public int productSearchWithTextNum(String searchText) { //�ܾ�� ��ȸ ����
		return sqlSessionTemplate.selectOne("product.productSearchNameNum", searchText);
	}
	
	@Override
	public List<ProductDto> productSearchWithCategory(String categoryId, Map<String, Object> param) { // �з��� ��ȸ
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1) * numPerpage, numPerpage);
		List<ProductDto> searchCategoryList = sqlSessionTemplate.selectList("product.productSearchCategory", categoryId, rb);
		return searchCategoryList;
	}
	
	@Override
	public int productSearchWithCategoryNum(String categoryId) { // �з��� ��ȸ ����
		return sqlSessionTemplate.selectOne("product.productSearchCategoryNum", categoryId);
	}

	@Override
	public List<ProductDto> productSearchDual(SearchDto searchDto, Map<String, Object> param) { // �� �� ����Ͽ� ��ȸ
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1) * numPerpage, numPerpage);
		List<ProductDto> searchDualList = sqlSessionTemplate.selectList("product.productSearchDual", searchDto, rb);
		
		return searchDualList;
	}
	
	@Override
	public int productSearchDualNum(SearchDto searchDto) { // �Ѵ� ����Ͽ� ��ȸ ����
		return sqlSessionTemplate.selectOne("product.productSearchDualNum", searchDto);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}