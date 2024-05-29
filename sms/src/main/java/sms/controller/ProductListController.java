package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sms.service.ProductSvc;

@Controller
public class ProductListController {
	//사용자 메인화면 + 사용자 제품 전체 조회
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/customermain", method=RequestMethod.GET) // 수정, 페이지 호출 입력
	public String productList(ModelMap model) {
		model.addAttribute("productList", productSvc.productList());
		
		return "product/productMain"; //수정, 페이지 위치 입력
	}

	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}