package sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.service.CartService;
import sms.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@Autowired
	CartService cartService;

	/**
	 * 재고를 확인하고 결제 페이지로 이동합니다.
	 * @param userId 사용자 ID
	 * @param model 모델 객체
	 * @return 결제 페이지 또는 재고 부족 경고 페이지
	 */
	@RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	public String checkStock(@RequestParam("user_id") String user_id, ModelMap model) {


		String user_id1 = "DGo9fGM";
		System.out.println("Received user_id: " + user_id1);

		List<CartDto> cartItems = cartService.listCartItems(user_id1);
		boolean outOfStock = false;
		for (CartDto item : cartItems) {
			int availableStock = cartService.getStock(item.getProduct_id());
			if (availableStock < item.getQuantity()) {
				outOfStock = true;
				break;
			}
		}
		if (outOfStock) {
			model.addAttribute("error", "장바구니에 있는 일부 상품의 재고가 부족합니다.");
			model.addAttribute("cartItems", cartItems);
			return "cart/cart_itemList";
		}
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", user_id1);
		return "pay/payPage";
	}
	/**
	 * 결제 정보를 저장합니다.
	 * @param paymentDto 결제 정보
	 * @param model 모델 객체
	 * @return 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaymentInfo(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // 테스트용 하드코딩된 userId
		paymentService.savePaymentInfo(paymentDto);
		return "payment/payInner";
	}



	/**
	 * 결제 기록을 생성합니다.
	 * @param paymentDto 결제 기록 정보
	 * @param model 모델 객체
	 * @return 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPaymentRecord(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // 테스트용 하드코딩된 userId
		paymentService.createPaymentRecord(paymentDto);
		return "redirect:/payment/payPage";
	}

	/**
	 * 결제 기록과 새로운 장바구니를 생성합니다.
	 * @param paymentDto 결제 기록 정보
	 * @param model 모델 객체
	 * @return 결제 완료 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/createWithNewCart", method = RequestMethod.POST)
	public String createPaymentRecordAndNewCart(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // 테스트용 하드코딩된 userId
		paymentService.createPaymentRecordAndNewCart(paymentDto);
		return "redirect:/payment/payInner";
	}

	/**
	 * 결제 진행 페이지를 보여줍니다.
	 * @param model 모델 객체
	 * @return 결제 진행 JSP 페이지
	 */
	@RequestMapping(value = "/payPage", method = RequestMethod.GET)
	public String showPayPage(@RequestParam("user_id") String user_id, ModelMap model) {
		List<CartDto> cartItems = cartService.listCartItems(user_id);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", user_id);
		return "pay/payPage";
	}



	/**
	 * 결제 정보 입력을 처리합니다.
	 * @param userId 사용자 ID
	 * @param receiverName 수령인 이름
	 * @param receiverAddress 수령인 주소
	 * @param model 모델 객체
	 * @return 금액 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST)
	public String processPayment(
			@RequestParam("user_id") String userId,
			@RequestParam("receiver_name") String receiverName,
			@RequestParam("receiver_address") String receiverAddress,
			ModelMap model) {

		// 임시 DTO에 수령인 정보 저장
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setUser_id(userId);
		paymentDto.setReceiver_name(receiverName);
		paymentDto.setReceiver_address(receiverAddress);

		// 모델에 DTO 저장
		model.addAttribute("paymentInfo", paymentDto);

		return "/pay/payInner";
	}

	/**
	 * 금액 결제 정보를 입력받고 결제를 완료합니다.
	 * @param payerName 입금자 이름
	 * @param payerAccount 입금자 계좌
	 * @param paymentInfo 이전 단계에서 저장된 결제 정보
	 * @param model 모델 객체
	 * @return 결제 완료 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/completePayment", method = RequestMethod.POST)
	public String completePayment(
			@RequestParam("user_id") String userId, 
			@RequestParam("payer_name") String payerName,
			@RequestParam("payer_account") String payerAccount, 
			@RequestParam("receiver_name") String receiverName,
			@RequestParam("receiver_address") String receiverAddress,
			@RequestParam("cart_id") String cartId,
			ModelMap model) {

				
		// 결제 정보 저장 로직 호출
		PaymentDto paymentDto = new PaymentDto(userId, receiverName, receiverAddress, payerName, payerAccount, cartId);

		// 결제 완료 후 새로운 장바구니 생성 및 상태 업데이트
		cartService.completePaymentAndCreateNewCart(paymentDto);

		// 결제 성공 후 상태 업데이트
		CartDto cartStatusUpdateDto = new CartDto();
		cartStatusUpdateDto.setUser_id(userId);
		cartStatusUpdateDto.setState("결제완료");
		cartService.updateCartState(cartStatusUpdateDto);

		return "/pay/paymentSuccess";

	}


}