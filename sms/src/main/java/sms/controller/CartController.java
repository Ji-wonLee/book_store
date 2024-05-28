package sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/cart")
	public String viewCart(@RequestParam("userId") String userId, Model model) {
		// ������� ��ٱ��� �׸��� ��ȸ�Ͽ� �信 ����
		model.addAttribute("cartItems", cartService.listCartItems(userId));
		return "cartView";  // ��ٱ��� ������ �並 ��ȯ
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("userId") String userId, @RequestParam("productId") String productId, @RequestParam("quantity") int quantity) {
		// ��û�� ��ǰ�� ��ٱ��Ͽ� �߰�
		cartService.addProductToCart(userId, productId, quantity);
		return "redirect:/cart?userId=" + userId;  // ��ٱ��� �������� �����̷�Ʈ
	}

	@GetMapping("/checkout")
	public String checkout(@RequestParam("userId") String userId) {
		// ���� �������� ���� �� ��ٱ��� ���¸� '������'���� ������Ʈ
		cartService.proceedToCheckout(userId);
		return "checkout";  // ���� ������ �並 ��ȯ
	}

	@PostMapping("/completePayment")
	public String completePayment(@RequestParam("userId") String userId,
			@RequestParam("recipientName") String recipientName,
			@RequestParam("recipientAddress") String recipientAddress,
			@RequestParam("payerName") String payerName,
			@RequestParam("payerAccount") String payerAccount) {
		// ���� ������ �����ϰ� ���� �Ϸ� ó��
		cartService.completePayment(userId, recipientName, recipientAddress, payerName, payerAccount);
		return "redirect:/confirmationPage";  // ���� Ȯ�� �������� �����̷�Ʈ
	}

	// ���� �Ϸ� �� ó���� ����ϴ� �޼ҵ�
	@PostMapping("/finalizePayment")
	public String finalizePayment(@RequestParam("userId") String userId, 
			@RequestParam("recipientName") String recipientName, 
			@RequestParam("recipientAddress") String recipientAddress, 
			@RequestParam("payerName") String payerName, 
			@RequestParam("payerAccount") String payerAccount) {
		cartService.completePayment(userId, recipientName, recipientAddress, payerName, payerAccount);
		return "redirect:/confirmationPage";  // ���� Ȯ�� �������� �����̷�Ʈ
	}

}