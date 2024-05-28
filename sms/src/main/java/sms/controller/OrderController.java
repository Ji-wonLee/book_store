package sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.ProductDto;
import sms.service.OrderSvc;
import sms.service.impl.OrderSvcImpl;

@Controller
public class OrderController {
	@Autowired
	private OrderSvc orderSvc;
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String registerUser( ModelMap model) {
		//@RequestParam("id") int id, 
		
		List<ProductDto> listProduct = orderSvc.invenList();
		model.addAttribute("listProduct", listProduct);
		
		
		//return "user/userRegisterPage";
		return "goodsOrderPage";
	}
}
