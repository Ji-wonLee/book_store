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
	// ��ǰ �� ȭ��
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String productInfo(@RequestParam(value="productId") String productId, ModelMap model) {
		
		model.addAttribute("productName", productSvc.productInfo(productId).getName());
		model.addAttribute("description", productSvc.productInfo(productId).getDescription());
		model.addAttribute("price", productSvc.productInfo(productId).getPrice());
		model.addAttribute("manufactureName", productSvc.productInfo(productId).getManufactureName());
		model.addAttribute("manufactureAddress", productSvc.productInfo(productId).getManufactureAddress());
		model.addAttribute("categoryName", productSvc.productInfo(productId).getCategoryName());
		model.addAttribute("imgurl", productSvc.productInfo(productId).getImgurl());
		model.addAttribute("page", productSvc.productInfo(productId).getPage());
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
