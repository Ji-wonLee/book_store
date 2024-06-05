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

import sms.dto.OrderSearchDto;
import sms.dto.ProductDto;
import sms.dto.SearchDto;
import sms.service.OrderSvc;
import sms.service.ProductSvc;

@Controller
public class OrderController {
	@Autowired
	private OrderSvc orderSvc;
	@Autowired
	private ProductSvc productSvc;

	//��ǰ��� ��� -> ���ְ����ϰ� (���ǻ�ǰ ����)
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order( ModelMap model) {
		//System.out.println("order");
		List<ProductDto> listProduct = orderSvc.invenList();
		model.addAttribute("listProduct", listProduct);		

		return "order/goodsOrderPage";
	}
	//���� ��ǰ Ȯ��
	@RequestMapping(value = "/orderCheck", method = RequestMethod.GET)
	public String orderCheck(@RequestParam Map<String, Integer> paramMap,ModelMap model) {	
		model.addAttribute("orderList", paramMap);
		return "order/orderCheck";
	}
	//���ֳ�
	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
	public String orderComplete(@RequestParam Map<String, String> paramMap, HttpServletResponse response ,ModelMap model) {				
		orderSvc.orderSave(paramMap);
		return "menu/admin";
	}
	
	//���� ��ǰ�ڵ�, ��ǰ�̸�, ī�װ� �з�, ���� �˻�
	@RequestMapping(value = "/orderSearch", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="category_id") String category_id,
							 @RequestParam(value="searchtext") String searchtext,
							 @RequestParam(value="remaining") int remaining,
							 ModelMap model) {
		OrderSearchDto orderSearchDto = new OrderSearchDto(searchtext, category_id,remaining );
		//ī�װ� �����ϴ� ����Ʈ
		model.addAttribute("categorylist", productSvc.categoryList());
		//�˻��� product����Ʈ
		List<ProductDto> listProduct = orderSvc.invenList();//����
		model.addAttribute("listProduct", listProduct);		
		//model.addAttribute("productlist", productSvc.productSearchList(searchDto));
		return "product/productMain";
	}
	
	//������ ����ȭ������
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
