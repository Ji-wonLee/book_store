package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.service.ProductSvc;

@Controller
public class ProductInfoController {
	// 상품 상세 화면
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String productInfo(@RequestParam(value="productId") String productId, ModelMap model) {
		
		model.addAttribute("productId", productSvc.productInfo(productId).getName());
		model.addAttribute("productId", productSvc.productInfo(productId).getDescription());
		model.addAttribute("productId", productSvc.productInfo(productId).getPrice());
		model.addAttribute("productId", productSvc.productInfo(productId).getManufactureName());
		model.addAttribute("productId", productSvc.productInfo(productId).getManufactureAddress());
		model.addAttribute("productId", productSvc.productInfo(productId).getCategoryName());
		model.addAttribute("productId", productSvc.productInfo(productId).getImgurl());
		model.addAttribute("productId", productSvc.productInfo(productId).getPage());
		model.addAttribute("productId", productSvc.productInfo(productId).getState());
		
		return "";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}
