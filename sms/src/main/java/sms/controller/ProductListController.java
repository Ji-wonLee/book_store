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
public class ProductListController {
	//����� ����ȭ�� + ����� ��ǰ ��ü ��ȸ
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/customermain", method=RequestMethod.GET) // ����, ������ ȣ�� �Է�
	public String productList(ModelMap model) {
		List<Category> categorylist = productSvc.categoryList();
		model.addAttribute("categorylist",categorylist);
		model.addAttribute("productlist", productSvc.productList());
		return "product/productMain"; //����, ������ ��ġ �Է�
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="category") Category category,
							 @RequestParam(value="searchtext") String searchtext,
							 ModelMap model) {
		SearchDto searchDto = new SearchDto(searchtext, category.getCategory_id());
		model.addAttribute("category", productSvc.categoryList());
		model.addAttribute("productlist", productSvc.productSearchList(searchDto));
		return "product/productMain";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}