package sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.service.ProductSvc;

@Controller
public class ProductInfoController {
	
	@Autowired
	private ProductSvc productSvc;
	
	@RequestMapping(value="/bookInfo", method=RequestMethod.GET)
	public String productInfo(@RequestParam(value="product_id") String productId, ModelMap model, HttpServletRequest req) {
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", userId);
		
		model.addAttribute("product_id", productId);
		model.addAttribute("product_imgurl", productSvc.productInfo(productId).getProduct_imgurl());
		model.addAttribute("product_name", productSvc.productInfo(productId).getProduct_name());
		model.addAttribute("category_name", productSvc.productInfo(productId).getCategory_name());
		model.addAttribute("product_page", productSvc.productInfo(productId).getProduct_page());
		model.addAttribute("description", productSvc.productInfo(productId).getDescription());
		model.addAttribute("manufacture_name", productSvc.productInfo(productId).getManufacture_name());
		model.addAttribute("product_price", productSvc.productInfo(productId).getProduct_price());
		model.addAttribute("state", productSvc.productInfo(productId).getState());
		model.addAttribute("quantity", productSvc.productInfo(productId).getQuantity());
		System.out.println(productSvc.productInfo(productId).getQuantity());
		return "product/productInfo";
	}
	
	
	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}
}