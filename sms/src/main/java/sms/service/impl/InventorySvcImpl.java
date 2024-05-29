package sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.InventoryDao;
import sms.dto.Inventory;
import sms.dto.ProductDto;
import sms.dto.SearchDto;
import sms.service.InventorySvc;

@Service
public class InventorySvcImpl implements InventorySvc {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public List<ProductDto> inventoryList() {
		List<ProductDto> inventoryList = inventoryDao.inventoryList();
		return inventoryList;
	}
	
//	@Override
//	public List<ProductDto> inventorySearchList(SearchDto searchDto){
//		List<ProductDto> inventorySearchList;
//		
//		if(searchDto.getCategory_id().isEmpty() == true) {
//			inventorySearchList = inventoryDao.inventorySearchWithText(searchDto.getSearchText());
//		} else if(searchDto.getSearchText().isEmpty() == true) {
//			inventorySearchList = inventoryDao.inventorySearchWithCategory(searchDto.getCategory_id());
//		} else {
//			inventorySearchList = inventoryDao.inventorySearchDual(searchDto);
//		}
//
//		return inventorySearchList;
//	}
	
	@Override
	public List<ProductDto> inventorySearchWithText(String searchText) {
		List<ProductDto> inventoryTextList = inventoryDao.inventorySearchWithText(searchText);
		return inventoryTextList;
	}
	
//	@Override
//	public List<ProductDto> inventorySearchWithCategory(String categoryId) {
//		List<ProductDto> inventoryCategoryList = inventoryDao.inventorySearchWithCategory(categoryId);
//		return inventoryCategoryList;
//	}
//	@Override
//	public List<ProductDto> inventorySearchDual(SearchDto searchDto) {
//		List<ProductDto> inventoryDualList = inventoryDao.inventorySearchDual(searchDto);
//		return inventoryDualList;
//	}

	@Override
	public int iventoryUpdate(Inventory inventory) {
		int updateNum = inventoryDao.iventoryUpdate(inventory);
		return updateNum;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
}
