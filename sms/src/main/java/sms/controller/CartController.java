package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dao.CartDao;
import sms.dto.CartDto;
import sms.dto.ProductDto;
import sms.service.CartService;

import java.util.List;

@Controller
public class CartController {

	@Autowired
	CartService cartService; 
	


	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String testMethod(ModelMap model) {
	    model.addAttribute("message", "�׽�Ʈ ������");
	    return "test"; 
	}

	   /**
     * Ư�� ������� ��ٱ��� �׸��� ��ȸ�մϴ�.
     * @param userId ����� ID
     * @param model �� ��ü
     * @return ��ٱ��� �׸� ����Ʈ�� �����ִ� JSP ������
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCartItems(@RequestParam(name = "user_id", required = false) String user_id, ModelMap model) {
        if (user_id == null || user_id.isEmpty()) {
        	user_id = "DGo9fGM"; 
        }
        List<CartDto> cartItems = cartService.listCartItems(user_id);
        if (cartItems.isEmpty()) {
            model.addAttribute("��ٱ��Ͽ� �׸��� �����ϴ�.");
        } else {
            model.addAttribute("cartItems", cartItems);
        }
        model.addAttribute("user_id", user_id); // userId�� �Բ� �𵨿� �߰�
        return "cart/cart_itemList"; // JSP ������ ���
    }
	/**
	 * ��ٱ��� �� ��ǰ�� ������ ������Ʈ�ϰ� �Ѿ��� �����մϴ�.
	 * @param cartItemUpdateDto ������Ʈ�� ��ǰ ����
	 * @param model �� ��ü
	 * @return ��ٱ��� ����Ʈ �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCartItemAndTotal(@ModelAttribute CartDto cartItemUpdateDto, ModelMap model) {
		cartService.updateCartItemAndTotal(cartItemUpdateDto);
		return "redirect:/cart";
	}

	/**
	 * ��ٱ��Ͽ� ��ǰ�� �߰��մϴ�.
	 * @param cartItemUpdateDto �߰��� ��ǰ ����
	 * @param model �� ��ü
	 * @return ��ٱ��� ����Ʈ �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductToCartDetails(@ModelAttribute CartDto cartItemUpdateDto, ModelMap model) {
		cartService.addProductToCartDetails(cartItemUpdateDto);
		return "redirect:/cart";
	}

	/**
	 * ��ٱ��� ���¸� ������Ʈ�մϴ�.
	 * @param cartStatusUpdateDto ������Ʈ�� ���� ����
	 * @param model �� ��ü
	 * @return ��ٱ��� ����Ʈ �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public String updateCartState(@ModelAttribute CartDto cartStatusUpdateDto, ModelMap model) {
		cartService.updateCartState(cartStatusUpdateDto);
		return "redirect:/cart";
	}


}