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
	
	@RequestMapping(value="/addItem", method=RequestMethod.GET) // ��ǰ�� Cart�� �߰�
	public String insertCart(@RequestParam("product_id") String product_id,
							 @RequestParam("number") int number,
							 @RequestParam("product_price") int product_price,
							 @RequestParam("product_Name") String product_Name,
							 ModelMap model, HttpServletRequest req) {
		
		HttpSession session=req.getSession();
		String userId = (String) session.getAttribute("user_id");
		// Session���� ����� Id�� �����ɴϴ�.
		
		String cart_id = selectSvc.findCartId(product_Name);
		// ���� ����ڰ� ��밡���� Cart_id�� �޾ƿɴϴ�.
		
		CartDto cartDto = new CartDto(cart_id, product_id, number, product_price);
		int updateNum = selectSvc.addCart(cartDto);
		System.out.println(updateNum + "�� ����");
		// CartDto(String cartId, String productId, int quantity,int price)
		// CartDto�� ������ �޾Ƽ� Cart_Detail ���̺� �ʿ��� ��ü���� SVC�� �Ѱ��ݴϴ�.
		// �߰��� ��ǰ ������ console���� Ȯ���� �� �ֽ��ϴ�.
		
		return "/cart/list";//Cart ȭ������ �Ѱ��ּ���.
	}
	
	public SelectSvc getSelectSvc() {
		return selectSvc;
	}

	public void setSelectSvc(SelectSvc selectSvc) {
		this.selectSvc = selectSvc;
	}
}
