package sms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.ProductDto;
import sms.service.OrderSvc;

@Controller
public class OrderController {
	@Autowired
	private OrderSvc orderSvc;

	//상품목록 출력 -> 발주가능하게 (절판상품 제외)
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order( ModelMap model) {
		//System.out.println("order");
		List<ProductDto> listProduct = orderSvc.invenList();
		model.addAttribute("listProduct", listProduct);		

		return "order/goodsOrderPage";
	}
	@RequestMapping(value = "/orderCheck", method = RequestMethod.GET)
	public String orderCheck(@RequestParam Map<String, Integer> paramMap,ModelMap model) {	
		model.addAttribute("orderList", paramMap);
		return "order/orderCheck";
	}
	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
	public String orderComplete(@RequestParam Map<String, String> paramMap, HttpServletResponse response ,ModelMap model) {				
		orderSvc.orderSave(paramMap);
		return "menu/admin";
	}
	
	//관리자 메인화면으로
	@RequestMapping(value = "/toAdminMain", method = RequestMethod.GET)
	public String toAdminMain(ModelMap model) {	
		return "menu/admin";
	}
	
	public OrderSvc getOrderSvc() {
		return orderSvc;
	}

	public void setOrderSvc(OrderSvc orderSvc) {
		this.orderSvc = orderSvc;
	}


}
