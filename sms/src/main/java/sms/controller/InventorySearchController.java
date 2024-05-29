package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.service.InventorySvc;

@Controller
public class InventorySearchController {
	// 재고 이름, ID 검색 화면

	@Autowired
	private InventorySvc inventorySvc;
	
	@RequestMapping(value="/inventorysearch", method = RequestMethod.GET)
	public String searchList(@RequestParam(value = "searchText") String searchText,
							 ModelMap model) {
		model.addAttribute("inventoryList", inventorySvc.inventorySearchWithText(searchText));
		return "inventory/inventorySearch";
	}
	
	public InventorySvc getInventorySvc() {
		return inventorySvc;
	}

	public void setInventorySvc(InventorySvc inventorySvc) {
		this.inventorySvc = inventorySvc;
	}
}
