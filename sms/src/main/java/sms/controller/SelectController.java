package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.CartDto;
import sms.dto.SelectDto;
import sms.service.SelectSvc;

@Controller
public class SelectController {
	
	@Autowired
	private SelectSvc selectSvc;

	public String insertCart(@RequestParam() String product_id,
							 @RequestParam() int number,
							 @RequestParam() int product_price,
							 @RequestParam() String product_Name,
							 ModelMap model) {
		
		// �ʿ��� ���� �߰��ؼ� CartDto�� �޾��ּ���.
		CartDto cartDto = new CartDto();
		//
		
		return "";//Cart ȭ��
	}
	
	public SelectSvc getSelectSvc() {
		return selectSvc;
	}

	public void setSelectSvc(SelectSvc selectSvc) {
		this.selectSvc = selectSvc;
	}
}
