package sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		 System.out.println("Received user_id: " + user_id);
		
		List<CartDto> cartItems = cartService.listCartItems(user_id);
	    boolean outOfStock = false;
	    for (CartDto item : cartItems) {
	        int availableStock = cartService.getStock(item.getProduct_id());
	        if (availableStock < item.getQuantity()) {
	            outOfStock = true;
	            break;
	        }
	    }
	    if (outOfStock) {
	        model.addAttribute("error", "Some items in your cart are out of stock.");
	        model.addAttribute("cartItems", cartItems);
	        return "cart/cart_itemList";
	    }
	    model.addAttribute("cartItems", cartItems);
	    model.addAttribute("user_id", user_id);
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
        paymentDto.setUser_id("defaultUserId"); // 테스트용 하드코딩된 userId
        paymentService.savePaymentInfo(paymentDto);
        return "payment/payInner";
    }

    /**
     * 결제 상태를 '결제완료'로 업데이트합니다.
     * @param cartStatusUpdateDto 업데이트할 상태 정보
     * @param model 모델 객체
     * @return 결제 완료 페이지로 리다이렉트
     */
    @RequestMapping(value = "/updateStateToCompleted", method = RequestMethod.POST)
    public String updateCartStateToCompleted(CartDto cartStatusUpdateDto, ModelMap model) {
        cartStatusUpdateDto.setUser_id("defaultUserId"); // 테스트용 하드코딩된 userId
        paymentService.updateCartStateToCompleted(cartStatusUpdateDto);
        return "redirect:/payment/payInner";
    }

    
    /**
     * 결제 완료를 처리합니다.
     * @param userId 사용자 ID
     * @param payerName 입금자 명
     * @param payerAccount 입금자 계좌
     * @param model 모델 객체
     * @return 결제 완료 페이지로 리다이렉트
     */
    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
    public String completePayment(@RequestParam("user_id") String userId, @RequestParam("payerName") String payerName,
                                  @RequestParam("payerAccount") String payerAccount, ModelMap model) {
        // 결제 완료 처리 로직을 여기에 추가하세요.
        model.addAttribute("message", "결제가 완료되었습니다.");
        return "pay/paymentSuccess";
    }

    /**
     * 결제 기록을 생성합니다.
     * @param paymentDto 결제 기록 정보
     * @param model 모델 객체
     * @return 결제 페이지로 리다이렉트
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPaymentRecord(PaymentDto paymentDto, ModelMap model) {
        paymentDto.setUser_id("defaultUserId"); // 테스트용 하드코딩된 userId
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
        paymentDto.setUser_id("defaultUserId"); // 테스트용 하드코딩된 userId
        paymentService.createPaymentRecordAndNewCart(paymentDto);
        return "redirect:/payment/payInner";
    }

    /**
     * 결제 진행 페이지를 보여줍니다.
     * @param model 모델 객체
     * @return 결제 진행 JSP 페이지
     */
    @RequestMapping(value = "/payPage", method = RequestMethod.GET)
    public String showPayPage(@RequestParam("userId") String userId, ModelMap model) {
        List<CartDto> cartItems = cartService.listCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("userId", userId);
        return "pay/payPage";
    }

    /**
     * 결제 내역 확인 페이지를 보여줍니다.
     * @param model 모델 객체
     * @return 결제 내역 확인 JSP 페이지
     */
    @RequestMapping(value = "/payInner", method = RequestMethod.GET)
    public String showPayInner(ModelMap model) {
        String userId = "defaultUserId"; // 테스트용 하드코딩된 userId
        double totalAmount = calculateTotalAmount(userId);
        model.addAttribute("totalAmount", totalAmount);
        return "payInner";
    }

    /**
     * 결제 요청을 처리합니다.
     * @param paymentDto 결제 정보
     * @param model 모델 객체
     * @return 결제 확인 페이지로 리다이렉트
     */
    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    public String processPayment(@ModelAttribute PaymentDto paymentDto, ModelMap model) {
        String user_id = paymentDto.getUser_id();
        List<CartDto> cartItems = cartService.listCartItems(user_id);

        boolean outOfStock = false;
        for (CartDto item : cartItems) {
            int availableStock = cartService.getStock(item.getProduct_id());
            if (availableStock < item.getQuantity()) {
                outOfStock = true;
                break;
            }
        }

        if (outOfStock) {
            model.addAttribute("error", "Some items in your cart are out of stock.");
            model.addAttribute("cartItems", cartItems);
            return "pay/payPage";
        }

        paymentService.savePaymentInfo(paymentDto);
        return "redirect:/payment/payInner";
    }
    /**
     * 전체 결제 금액을 계산합니다.
     * @param userId 사용자 ID
     * @return 전체 결제 금액
     */
    private double calculateTotalAmount(String userId) {
        // 전체 결제 금액 계산 로직을 구현
        // 예시로 0.0을 반환
        return 0.0;
    }
}