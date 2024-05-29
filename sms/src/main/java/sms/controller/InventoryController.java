package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.service.InventorySvc;

@Controller
public class InventoryController {
	// 재고 메인 화면
	
	@Autowired
	private InventorySvc inventorySvc;
	
	@RequestMapping(value="/inventory", method = RequestMethod.GET)
	public String inventoryList(ModelMap model) {
		
		model.addAttribute("inventoryList", inventorySvc.inventoryList());
		
		return "inventory/inventoryList";
	}
	
	public InventorySvc getInventorySvc() {
		return inventorySvc;
	}

	public void setInventorySvc(InventorySvc inventorySvc) {
		this.inventorySvc = inventorySvc;
	}
}