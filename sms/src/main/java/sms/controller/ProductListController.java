package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sms.service.ProductSvc;

@Controller
public class ProductListController {
	//����� ����ȭ�� + ����� ��ǰ ��ü ��ȸ
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/customermain", method=RequestMethod.GET) // ����, ������ ȣ�� �Է�
	public String productList(ModelMap model) {
		model.addAttribute("productList", productSvc.productList());
		
		return "product/productMain"; //����, ������ ��ġ �Է�
	}

	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}