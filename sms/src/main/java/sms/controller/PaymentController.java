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
     * 결제 정보를 저장합니다.
     * @param paymentDto 결제 정보
     * @param model 모델 객체
     * @return 결제 페이지로 리다이렉트
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePaymentInfo(PaymentDto paymentDto, ModelMap model) {
        paymentDto.setUser_id("defaultUserId"); // 테스트용 하드코딩된 userId
        paymentService.savePaymentInfo(paymentDto);
        return "redirect:/payment/payPage";
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
    public String showPayPage(ModelMap model) {
        String userId = "defaultUserId"; // 테스트용 하드코딩된 userId
        List<CartDto> cartItems = cartService.listCartItems(userId);
        model.addAttribute("cartItems", cartItems);
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
