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
	// 재고 메인 화면
	
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
        // 변경된 값 처리 로직
        // 예: 변경된 값을 로깅하거나 데이터베이스에 저장
		
		int updateSum = 0;
		int updateInt = 0;
        for (Map.Entry<String, String> entry : changedValues.entrySet()) {
        	Inventory inventory = new Inventory(entry.getKey(), Integer.valueOf(entry.getValue()));
        	updateInt = inventorySvc.iventoryUpdate(inventory);
        	updateSum = updateSum + updateInt;
        }

        // 클라이언트에게 응답
        return updateSum + "개 변경완료";
    }
	
	public InventorySvc getInventorySvc() {
		return inventorySvc;
	}

	public void setInventorySvc(InventorySvc inventorySvc) {
		this.inventorySvc = inventorySvc;
	}
}