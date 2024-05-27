package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sms.service.ProductSvc;

@Controller
public class ProductInfoController {
	
	@Autowired
	private ProductSvc productSvc;

	
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}
