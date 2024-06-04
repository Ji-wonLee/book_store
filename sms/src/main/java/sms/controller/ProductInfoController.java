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
	// ???¡Æ ??¨ù¨ù ?¡©¢¬?
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/bookInfo", method=RequestMethod.GET)
	public String productInfo(@RequestParam(value="product_id") String productId, ModelMap model) {
		model.addAttribute("product_id", productId);
		model.addAttribute("product_imgurl", productSvc.productInfo(productId).getProduct_imgurl());
		model.addAttribute("product_name", productSvc.productInfo(productId).getProduct_name());
		model.addAttribute("category_name", productSvc.productInfo(productId).getCategory_name());
		model.addAttribute("product_page", productSvc.productInfo(productId).getProduct_page());
		model.addAttribute("description", productSvc.productInfo(productId).getDescription());
		model.addAttribute("manufacture_name", productSvc.productInfo(productId).getManufacture_name());
		model.addAttribute("product_price", productSvc.productInfo(productId).getProduct_price());
		model.addAttribute("state", productSvc.productInfo(productId).getState());
		
		return "product/productInfo";
	}
	
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}