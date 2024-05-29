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
		
		model.addAttribute("productName", productSvc.productInfo(productId).getProduct_name());
		model.addAttribute("description", productSvc.productInfo(productId).getDescription());
		model.addAttribute("price", productSvc.productInfo(productId).getProduct_price());
		model.addAttribute("manufactureName", productSvc.productInfo(productId).getManufacture_name());
		model.addAttribute("manufactureAddress", productSvc.productInfo(productId).getManufacture_address());
		model.addAttribute("categoryName", productSvc.productInfo(productId).getCategory_name());
		model.addAttribute("imgurl", productSvc.productInfo(productId).getProduct_imgurl());
		model.addAttribute("page", productSvc.productInfo(productId).getProduct_page());
		model.addAttribute("state", productSvc.productInfo(productId).getState());
		
		return "";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}
