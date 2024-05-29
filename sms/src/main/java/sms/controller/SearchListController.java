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
	// 사용자 검색 화면
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/searchList", method=RequestMethod.GET)
	public String productSearchWithText(@RequestParam(value = "searchText") String searchText,
										@RequestParam(value = "category_id") String category_id,
										ModelMap model) {
		
		SearchDto searchDto = new SearchDto(searchText, category_id);
		
		model.addAttribute("categoryList", productSvc.categoryList());
		
		model.addAttribute("productList", productSvc.productSearchList(searchDto));
		
		return "product/productSearcList";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}	
}