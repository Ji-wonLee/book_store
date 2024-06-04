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
	
	@RequestMapping(value="/bookInfo", method=RequestMethod.GET)
	public String productInfo(@RequestParam(value="product_id") String product_id, ModelMap model) {
		
		model.addAttribute("product_imgurl", productSvc.productInfo(product_id).getProduct_imgurl());
		model.addAttribute("product_name", productSvc.productInfo(product_id).getProduct_name());
		model.addAttribute("category_name", productSvc.productInfo(product_id).getCategory_name());
		model.addAttribute("product_page", productSvc.productInfo(product_id).getProduct_page());
		model.addAttribute("description", productSvc.productInfo(product_id).getDescription());
		model.addAttribute("manufacture_name", productSvc.productInfo(product_id).getManufacture_name());
		model.addAttribute("product_price", productSvc.productInfo(product_id).getProduct_price());
		model.addAttribute("state", productSvc.productInfo(product_id).getState());
		
		return "product/productInfo";
	}
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}