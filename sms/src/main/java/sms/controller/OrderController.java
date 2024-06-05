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

	//상품목록 출력 -> 발주가능하게 (절판상품 제외)
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order( ModelMap model) {
		//System.out.println("order");
		List<ProductDto> listProduct = orderSvc.invenList();
		model.addAttribute("listProduct", listProduct);		

		return "order/goodsOrderPage";
	}
	//발주 상품 확인
	@RequestMapping(value = "/orderCheck", method = RequestMethod.GET)
	public String orderCheck(@RequestParam Map<String, Integer> paramMap,ModelMap model) {	
		model.addAttribute("orderList", paramMap);
		return "order/orderCheck";
	}
	//발주끝
	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
	public String orderComplete(@RequestParam Map<String, String> paramMap, HttpServletResponse response ,ModelMap model) {				
		orderSvc.orderSave(paramMap);
		return "menu/admin";
	}
	
	//발주 상품코드, 상품이름, 카테고리 분류, 개수 검색
	@RequestMapping(value = "/orderSearch", method = RequestMethod.GET)
	public String testSearch(@RequestParam(value="category_id") String category_id,
							 @RequestParam(value="searchtext") String searchtext,
							 @RequestParam(value="remaining") int remaining,
							 ModelMap model) {
		OrderSearchDto orderSearchDto = new OrderSearchDto(searchtext, category_id,remaining );
		//카테고리 선택하는 리스트
		model.addAttribute("categorylist", productSvc.categoryList());
		//검색한 product리스트
		List<ProductDto> listProduct = orderSvc.invenList();//수정
		model.addAttribute("listProduct", listProduct);		
		//model.addAttribute("productlist", productSvc.productSearchList(searchDto));
		return "product/productMain";
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
