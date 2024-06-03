package sms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.Inventory;
import sms.service.InventorySvc;

@Controller
public class InventoryController {
	// ��� ���� ȭ��
	
	@Autowired
	private InventorySvc inventorySvc;
	
	@RequestMapping(value="/inventory", method = RequestMethod.GET)
	public String inventoryList(ModelMap model) {
		model.addAttribute("inventorylist", inventorySvc.inventoryList());
		return "inventory/inventoryList";
	}
	
	@RequestMapping(value="/inventorysearch", method = RequestMethod.GET)
	public String inventorySearch(@RequestParam(value="searchtext") String searchtext, ModelMap model) {
		model.addAttribute("inventorylist", inventorySvc.inventorySearchWithText(searchtext));
		return "inventory/inventoryList";
	}
	
	@RequestMapping(value = "/handleChangedValues", method = RequestMethod.GET)
    public String handleChangedValues(@RequestBody  Map<String, String> changedValues) {
        // ����� �� ó�� ����
        // ��: ����� ���� �α��ϰų� �����ͺ��̽��� ����
		
		int updateSum = 0;
		int updateInt = 0;
        for (Map.Entry<String, String> entry : changedValues.entrySet()) {
        	Inventory inventory = new Inventory(entry.getKey(), Integer.valueOf(entry.getValue()));
        	updateInt = inventorySvc.iventoryUpdate(inventory);
        	updateSum = updateSum + updateInt;
        }

        // Ŭ���̾�Ʈ���� ����
        return updateSum + "�� ����Ϸ�";
    }
	
	public InventorySvc getInventorySvc() {
		return inventorySvc;
	}

	public void setInventorySvc(InventorySvc inventorySvc) {
		this.inventorySvc = inventorySvc;
	}
}