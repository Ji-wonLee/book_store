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
	} // ��� ��ǰ ��� ����Ʈ

	@Override
	public List<ProductDto> inventorySearchWithText(String searchText) {
		List<ProductDto> inventoryTextList = sqlSessionTemplate.selectList("product.inventorySearchName", searchText);
		return inventoryTextList;
	} // �˻����� �̻������� ��ǰ ��ȸ

//	@Override
//	public List<ProductDto> inventorySearchWithCategory(String categoryId) {
//		List<ProductDto> inventoryCategoryList = sqlSessionTemplate.selectList("product.inventorySearchCategory", categoryId);
//		return inventoryCategoryList;
//	} // �˻����� �з��� ��ǰ ��ȸ
//
//	@Override
//	public List<ProductDto> inventorySearchDual(SearchDto searchDto) {
//		List<ProductDto> inventoryDualList = sqlSessionTemplate.selectList("product.inventorySearchDual", searchDto);
//		return inventoryDualList;
//	} // �˻����� �з�, �Է°� ��� ���

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