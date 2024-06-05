package sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dto.CartDto;
import sms.service.SelectSvc;

@Controller
public class SelectController {
	
	@Autowired
	private SelectSvc selectSvc;
	
	@RequestMapping(value="/addItem", method=RequestMethod.GET) // 제품을 Cart에 추가
	public String insertCart(@RequestParam("product_id") String product_id,
							 @RequestParam("number") int number,
							 @RequestParam("product_price") int product_price,
							 @RequestParam("product_Name") String product_Name,
							 ModelMap model, HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		// Session에서 사용자 Id를 가져옵니다.
		
		String cart_id = selectSvc.findCartId(product_Name);
		// 현재 사용자가 사용가능한 Cart_id를 받아옵니다.
		
		CartDto cartDto = new CartDto(cart_id, product_id, number, product_price);
		int updateNum = selectSvc.addCart(cartDto);
		System.out.println(updateNum + "행 갱신");
		// CartDto(String cartId, String productId, int quantity,int price)
		// CartDto의 값으로 받아서 Cart_Detail 테이블에 필요한 객체만을 SVC로 넘겨줍니다.
		// 추가된 상품 개수를 console에서 확인할 수 있습니다.
		
		return "/cart/list";//Cart 화면으로 넘겨주세요.
	}
	
	public SelectSvc getSelectSvc() {
		return selectSvc;
	}

	public void setSelectSvc(SelectSvc selectSvc) {
		this.selectSvc = selectSvc;
	}
}
