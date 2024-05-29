package sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String order( ModelMap model) {
		System.out.println("order");
		List<ProductDto> listProduct = orderSvc.invenList();
		model.addAttribute("listProduct", listProduct);		
		
		return "order/goodsOrderPage";
	}
	@RequestMapping(value = "/orderCheck", method = RequestMethod.POST)
	public String orderCheck(@RequestParam Map<String, Integer> paramMap,ModelMap model) {	
		model.addAttribute("orderList", paramMap);
		return "order/orderCheck";
	}
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(@RequestParam Map<String, String> paramMap,ModelMap model) {				
		System.out.println("orderComplete1");
		orderSvc.orderSave(paramMap);
		System.out.println("orderComplete2");
		return null;
	}
	public OrderSvc getOrderSvc() {
		return orderSvc;
	}

	public void setOrderSvc(OrderSvc orderSvc) {
		this.orderSvc = orderSvc;
	}
	
	
}
