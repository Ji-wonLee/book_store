package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.SearchDto;
import sms.service.ProductSvc;

@Controller
public class SearchListController {
	// ����� �˻� ȭ��
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="", method=RequestMethod.GET) //���� �ʿ�, ������ ȣ��
	public String productSearchWithText(@RequestParam(value = "searchText") String searchText,
										@RequestParam(value = "categoryId") String categoryId,
										ModelMap model) {
		
		SearchDto searchDto = new SearchDto(searchText, categoryId);
		
		model.addAttribute("productList", productSvc.productSearchList(searchDto));
		
		return "";
	}
	
	
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}	
}