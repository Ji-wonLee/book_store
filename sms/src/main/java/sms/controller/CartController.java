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
		// 사용자의 장바구니 항목을 조회하여 뷰에 전달
		model.addAttribute("cartItems", cartService.listCartItems(userId));
		return "cartView";  // 장바구니 페이지 뷰를 반환
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("userId") String userId, @RequestParam("productId") String productId, @RequestParam("quantity") int quantity) {
		// 요청된 상품을 장바구니에 추가
		cartService.addProductToCart(userId, productId, quantity);
		return "redirect:/cart?userId=" + userId;  // 장바구니 페이지로 리다이렉트
	}

	@GetMapping("/checkout")
	public String checkout(@RequestParam("userId") String userId) {
		// 결제 페이지로 진행 전 장바구니 상태를 '결제중'으로 업데이트
		cartService.proceedToCheckout(userId);
		return "checkout";  // 결제 페이지 뷰를 반환
	}

	@PostMapping("/completePayment")
	public String completePayment(@RequestParam("userId") String userId,
			@RequestParam("recipientName") String recipientName,
			@RequestParam("recipientAddress") String recipientAddress,
			@RequestParam("payerName") String payerName,
			@RequestParam("payerAccount") String payerAccount) {
		// 결제 정보를 저장하고 결제 완료 처리
		cartService.completePayment(userId, recipientName, recipientAddress, payerName, payerAccount);
		return "redirect:/confirmationPage";  // 결제 확인 페이지로 리다이렉트
	}

	// 결제 완료 후 처리를 담당하는 메소드
	@PostMapping("/finalizePayment")
	public String finalizePayment(@RequestParam("userId") String userId, 
			@RequestParam("recipientName") String recipientName, 
			@RequestParam("recipientAddress") String recipientAddress, 
			@RequestParam("payerName") String payerName, 
			@RequestParam("payerAccount") String payerAccount) {
		cartService.completePayment(userId, recipientName, recipientAddress, payerName, payerAccount);
		return "redirect:/confirmationPage";  // 결제 확인 페이지로 리다이렉트
	}

}