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
	        model.addAttribute("error", "Some items in your cart are out of stock.");
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
     * 결제 상태를 '결제완료'로 업데이트합니다.
     * @param cartStatusUpdateDto 업데이트할 상태 정보
     * @param model 모델 객체
     * @return 결제 완료 페이지로 리다이렉트
     */
    @RequestMapping(value = "/updateStateToCompleted", method = RequestMethod.POST)
    public String updateCartStateToCompleted(CartDto cartStatusUpdateDto, ModelMap model) {
        cartStatusUpdateDto.setUser_id("DGo9fGM"); // 테스트용 하드코딩된 userId
        paymentService.updateCartStateToCompleted(cartStatusUpdateDto);
        return "redirect:/payment/payInner";
    }

    
//    /**
//     * 결제 완료를 처리합니다.
//     * @param userId 사용자 ID
//     * @param payerName 입금자 명
//     * @param payerAccount 입금자 계좌
//     * @param model 모델 객체
//     * @return 결제 완료 페이지로 리다이렉트
//     */
//    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
//    public String completePayment(@RequestParam("user_id") String userId, @RequestParam("payerName") String payerName,
//                                  @RequestParam("payerAccount") String payerAccount, ModelMap model) {
//        // 결제 완료 처리 로직을 여기에 추가하세요.
//        model.addAttribute("message", "결제가 완료되었습니다.");
//        return "pay/paymentSuccess";
//    }

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
     * 결제 내역 확인 페이지를 보여줍니다.
     * @param model 모델 객체
     * @return 결제 내역 확인 JSP 페이지
     */
    @RequestMapping(value = "/payInner", method = RequestMethod.GET)
    public String showPayInner(ModelMap model) {
        String user_id = "DGo9fGM"; // 테스트용 하드코딩된 userId
        double totalAmount = calculateTotalAmount(user_id);
        model.addAttribute("totalAmount", totalAmount);
        return "payInner";
    }

//    /**
//     * 결제 요청을 처리합니다.
//     * @param userId 사용자 ID
//     * @param receiverName 수령인 이름
//     * @param receiverAddress 수령인 주소
//     * @param model 모델 객체
//     * @return 결제 확인 페이지로 리다이렉트
//     */
//    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
//    public String processPayment(
//        @RequestParam("user_id") String userId,
//        @RequestParam("receiver_name") String receiverName,
//        @RequestParam("receiver_address") String receiverAddress,
//        ModelMap model) {
//
//        // 장바구니 목록 조회
//        List<CartDto> cartItems = cartService.listCartItems(userId);
//
//        // 재고 확인
//        boolean outOfStock = false;
//        for (CartDto item : cartItems) {
//            int availableStock = cartService.getStock(item.getProduct_id());
//            if (availableStock < item.getQuantity()) {
//                outOfStock = true;
//                break;
//            }
//        }
//
//        // 재고 부족 시 에러 처리
//        if (outOfStock) {
//            model.addAttribute("error", "재고가 없습니다.");
//            model.addAttribute("cartItems", cartItems);
//            return "pay/payPage";
//        }
//
//        // 결제 정보 생성 및 저장
//        PaymentDto paymentDto = new PaymentDto(userId, receiverName, receiverAddress);
//        paymentService.savePaymentInfo(paymentDto);
//
//        // 결제 내부 페이지로 리다이렉트
//        return "redirect:/payment/payInner";
//    }
    
//    /**
//     * 첫번째 컨트롤러 
//     * @param userId
//     * @param receiverName
//     * @param receiverAddress
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/storeReceiverInfo", method = RequestMethod.POST)
//    public String storeReceiverInfo(@RequestParam("user_id") String userId,
//                                    @RequestParam("receiver_name") String receiverName,
//                                    @RequestParam("receiver_address") String receiverAddress,
//                                    ModelMap model) {
//        // 데이터 저장 및 로직 처리
//        model.addAttribute("user_id", userId);  // 다음 페이지로 user_id 전달
//        return "forward:/paymentPage2"; // 다음 페이지로 전달
//    }
//
//
//    /**
//     * 입금자 정보를 받아 결제를 완료합니다.
//     * @param userId 사용자 ID
//     * @param payerName 입금자 이름
//     * @param payerAccount 입금자 계좌
//     * @param model 모델 객체
//     * @return 결제 완료 페이지로 리다이렉트
//     */
//    @PostMapping("/completePayment")
//    public String completePayment(
//        @RequestParam("user_id") String userId,
//        @RequestParam("payerName") String payerName,
//        @RequestParam("payerAccount") String payerAccount,
//        ModelMap model) {
//
//        // 여기에 결제 정보를 생성하고 저장하는 로직을 추가
//        PaymentDto paymentDto = new PaymentDto(userId, payerName, payerAccount);
//        paymentService.savePaymentInfo(paymentDto);
//
//        // 결제 정보와 관련된 추가 처리 로직을 실행
//        // 예: 장바구니 상태 업데이트, 주문 기록 생성 등
//        cartService.updateCartStateToCompleted(userId); // 장바구니 상태를 '결제 완료'로 업데이트
//
//        // 모델에 결제 완료 메시지 추가
//        model.addAttribute("message", "결제가 성공적으로 완료되었습니다.");
//
//        // 결제 완료 페이지로 리다이렉트
//        return "redirect:/payment/paymentSuccess";
//    }


    
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
//    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
//    public String completePayment(
//        @RequestParam("payerName") String payerName,
//        @RequestParam("payerAccount") String payerAccount,
//        @ModelAttribute("paymentInfo") PaymentDto paymentInfo,
//        ModelMap model) {
//
//        // 입금자 정보 추가
//        paymentInfo.setPayer_name(payerName);
//        paymentInfo.setPayer_account(payerAccount);
//
//        // 결제 정보 저장
//        paymentService.savePaymentInfo(paymentInfo);
//
//        return "redirect:/payment/confirmationPage"; // 결제 확인 페이지 경로
//    }
    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
    public String completePayment(
        @RequestParam("user_id") String userId, 
        @RequestParam("payer_name") String payerName,
        @RequestParam("payer_account") String payerAccount, 
        @RequestParam("receiver_name") String receiverName,
        @RequestParam("receiver_address") String receiverAddress,
        ModelMap model) {

        PaymentDto paymentDto = new PaymentDto(userId, payerName, payerAccount, receiverName, receiverAddress);
        try {
            paymentService.savePaymentInfo(paymentDto);
            return "/payment/paymentSuccess"; 
        } catch (Exception e) {
            e.printStackTrace(); // 예외 상세 출력 추가
            model.addAttribute("error", "결제 처리 중 오류가 발생했습니다: " + e.getMessage()); 
            return "/pay/errorPage";  
        }
    }

    @RequestMapping(value = "/paymentSuccess", method = RequestMethod.GET)
    public String paymentSuccess(ModelMap model) {
        model.addAttribute("message", "결제가 성공적으로 완료되었습니다.");
        return "pay/paymentSuccess"; // 결제 성공 페이지 JSP
    }

    
    /**
     * 전체 결제 금액을 계산합니다.
     * @param userId 사용자 ID
     * @return 전체 결제 금액
     */
    private double calculateTotalAmount(String user_id) {
        // 전체 결제 금액 계산 로직을 구현
        // 예시로 0.0을 반환
        return 0.0;
    }
}
