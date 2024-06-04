package sms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.InventoryDao;
import sms.dto.Inventory;
import sms.dto.ProductDto;

@Repository
public class InventoryDaoImpl implements InventoryDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<ProductDto> inventoryList() {
		List<ProductDto> inventoryList = sqlSessionTemplate.selectList("product.inventoryList");
		return inventoryList;
	} // 재고 상품 출력 리스트

	@Override
	public List<ProductDto> inventorySearchWithText(String searchText) {
		List<ProductDto> inventoryTextList = sqlSessionTemplate.selectList("product.inventorySearchName", searchText);
		return inventoryTextList;
	} // 검색에서 겁색값으로 상품 조회

//	@Override
//	public List<ProductDto> inventorySearchWithCategory(String categoryId) {
//		List<ProductDto> inventoryCategoryList = sqlSessionTemplate.selectList("product.inventorySearchCategory", categoryId);
//		return inventoryCategoryList;
//	} // 검색에서 분류로 상품 조회
//
//	@Override
//	public List<ProductDto> inventorySearchDual(SearchDto searchDto) {
//		List<ProductDto> inventoryDualList = sqlSessionTemplate.selectList("product.inventorySearchDual", searchDto);
//		return inventoryDualList;
//	} // 검색에서 분류, 입력값 모두 사용

	@Override
	public int iventoryUpdate(Inventory inventory) {
		int updateNum = sqlSessionTemplate.selectOne("product.updateQuantity", inventory);
		return updateNum;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}