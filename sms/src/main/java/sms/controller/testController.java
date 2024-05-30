package sms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.Category;
import sms.dto.SearchDto;
import sms.service.ProductSvc;

@Controller
public class testController {
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value = "/mainTest", method = RequestMethod.GET)
	public String testMain(ModelMap model) {
		List<Category> categorylist = productSvc.categoryList();
		model.addAttribute("categorylist",categorylist);
		System.out.println(productSvc.categoryList());
		model.addAttribute("productlist", productSvc.productList());
		return "product/testMain";
	}
	
	@RequestMapping(value = "/searchTest", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="category") Category category,
							 @RequestParam(value="searchtext") String searchtext,
							 ModelMap model) {
		SearchDto searchDto = new SearchDto(searchtext, category.getCategory_id());
		model.addAttribute("category", productSvc.categoryList());
		model.addAttribute("productlist", productSvc.productSearchList(searchDto));
		return "product/testMain";
	}
	public ProductSvc getProductSvc() {
		return productSvc;
	}
	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}