package sms.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import sms.dto.Inventory;
import sms.dto.PaymentDetailDto;
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
	 * @param req HttpServletRequest 객체
	 * @param model 모델 객체
	 * @return 결제 페이지 또는 재고 부족 경고 페이지
	 */
	@RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	public String checkStock(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id"); // 세션에서 사용자 ID를 가져옵니다.

		// 사용자의 장바구니 항목을 가져옵니다.
		List<CartDto> cartItems = cartService.listCartItems(userId);
		boolean outOfStock = false;
		// 각 항목에 대해 재고를 확인합니다.
		for (CartDto item : cartItems) {
			int availableStock = cartService.getStock(item.getProduct_id());
			if (availableStock < item.getQuantity()) {
				outOfStock = true; // 재고 부족 시 outOfStock을 true로 설정합니다.
				break;
			}
		}
		if (outOfStock) {
			// 재고가 부족하면 에러 메시지와 함께 장바구니 페이지로 돌아갑니다.
			model.addAttribute("error", "장바구니에 있는 일부 상품의 재고가 부족합니다.");
			model.addAttribute("cartItems", cartItems);
			return "cart/cart_itemList";
		}

		// 재고가 충분하면 결제 ID를 생성합니다.
		String cartId = cartService.findCartId(userId);
		String paymentId = paymentService.generatePaymentId(cartId);

		// PaymentDto 생성 및 저장
		PaymentDto paymentDto = new PaymentDto(userId, "", "", "", "", cartId);
		paymentDto.setPayment_id(paymentId);
		paymentService.savePaymentInfo(paymentDto);

		// Cart 상태를 '결제중'으로 업데이트합니다.
		CartDto cartStateUpdateDto = new CartDto(cartId, "결제중");
		cartService.updateCartState(cartStateUpdateDto);

		// 세션에 cart_id와 payment_id 저장
		session.setAttribute("cart_id", cartId);
		session.setAttribute("payment_id", paymentId);

		// 재고 업데이트: 구매된 수량만큼 재고에서 빼줍니다.
		for (CartDto item : cartItems) {
			cartService.updateStock(new Inventory(item.getProduct_id(), -item.getQuantity()));
			// PaymentDetailDto 생성 및 저장
			PaymentDetailDto paymentDetailDto = new PaymentDetailDto(paymentId, item.getProduct_id(), item.getQuantity(), item.getPrice());
			paymentService.savePaymentDetail(paymentDetailDto);
		}

		// 모델에 필요한 속성들을 추가합니다.
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", userId);
		model.addAttribute("payment_id", paymentId); // 생성된 payment_id를 모델에 추가합니다.
		return "pay/payPage"; // 결제 페이지로 이동합니다.
	}


	/**
	 * 결제 정보를 저장합니다.
	 * @param paymentDto 결제 정보
	 * @param req HttpServletRequest 객체
	 * @param model 모델 객체
	 * @return 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaymentInfo(PaymentDto paymentDto, HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		paymentDto.setUser_id(userId); 
		paymentService.savePaymentInfo(paymentDto);
		return "payment/payInner";
	}

	// 장바구니 상태 뒤로 변경
	@PostMapping("/revertPayment")
	public void revertPayment(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String cartId = (String) session.getAttribute("cart_id");
		if (cartId != null) {
			cartService.revertCartState(cartId);
		}
	}

	/**
	 * 결제 기록을 생성합니다.
	 * @param paymentDto 결제 기록 정보
	 * @param req HttpServletRequest 객체
	 * @param model 모델 객체
	 * @return 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPaymentRecord(PaymentDto paymentDto, HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		paymentDto.setUser_id(userId); 
		paymentService.createPaymentRecord(paymentDto);
		return "redirect:/payment/payPage";
	}

	/**
	 * 결제 기록과 새로운 장바구니를 생성합니다.
	 * @param paymentDto 결제 기록 정보
	 * @param req HttpServletRequest 객체
	 * @param model 모델 객체
	 * @return 결제 완료 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/createWithNewCart", method = RequestMethod.POST)
	public String createPaymentRecordAndNewCart(PaymentDto paymentDto, HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		paymentDto.setUser_id(userId); 
		paymentService.createPaymentRecordAndNewCart(paymentDto);
		return "redirect:/payment/payInner";
	}

	/**
	 * 결제 진행 페이지를 보여줍니다.
	 * @param req HttpServletRequest 객체
	 * @param model 모델 객체
	 * @return 결제 진행 JSP 페이지
	 */
	@RequestMapping(value = "/payPage", method = RequestMethod.GET)
	public String showPayPage(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		List<CartDto> cartItems = cartService.listCartItems(userId);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", userId);
		return "pay/payPage";
	}



	/**
	 * 결제 정보 입력을 처리합니다.
	 * @param req HttpServletRequest 객체
	 * @param receiverName 수령인 이름
	 * @param receiverAddress 수령인 주소
	 * @param model 모델 객체
	 * @return 금액 결제 페이지로 리다이렉트
	 */
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST)
	public String processPayment(
			HttpServletRequest req,
			@RequestParam("receiver_name") String receiverName,
			@RequestParam("receiver_address") String receiverAddress,
			ModelMap model) {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");

		// 임시 DTO에 수령인 정보 저장
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setUser_id(userId);
		paymentDto.setReceiver_name(receiverName);
		paymentDto.setReceiver_address(receiverAddress);

		// 세션에 수령인 정보 저장
		session.setAttribute("receiver_name", receiverName);
		session.setAttribute("receiver_address", receiverAddress);

		// 모델에 DTO 저장
		model.addAttribute("paymentInfo", paymentDto);

		return "pay/payInner";
	}


	@RequestMapping("/productMain")
	public String showProductMain() {
		return "product/productMain"; // 뷰 리졸버 설정에 따라 /WEB-INF/views/product/productMain.jsp로 매핑됨
	}

	/**
	 * 결제 완료 처리
	 * @param req HttpServletRequest 객체
	 * @param payerName 입금자 이름
	 * @param payerAccount 입금자 계좌
	 * @param response HttpServletResponse 객체
	 * @return 리다이렉션 URL
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/completePayment", method = RequestMethod.POST , produces = "text/html; charset=utf-8")
	public void completePayment(HttpServletRequest req,
			@RequestParam("payer_name") String payerName,
			@RequestParam("payer_account") String payerAccount,
			HttpServletResponse response) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8"); 
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		String cartId = (String) session.getAttribute("cart_id");
		String paymentId = (String) session.getAttribute("payment_id");
		String receiverName = (String) session.getAttribute("receiver_name");
		String receiverAddress = (String) session.getAttribute("receiver_address");

		// 로깅을 통해 세션 정보 확인
		System.out.println("Session user_id: " + userId);
		System.out.println("Session cart_id: " + cartId);
		System.out.println("Session payment_id: " + paymentId);

		if (cartId == null || cartId.isEmpty()) {
			throw new IllegalArgumentException("Invalid cartId");
		}

		// PaymentDto 생성 및 결제 정보 업데이트
		PaymentDto paymentDto = new PaymentDto(paymentId, userId, receiverName, receiverAddress, payerName, payerAccount, cartId);
		System.out.println("PaymentDto: " + paymentDto.toString()); // 디버깅용 로그 추가
		paymentService.updatePaymentInfo(paymentDto);

		// 장바구니 상태를 '결제완료'로 업데이트
		CartDto cartStateUpdateDto = new CartDto(cartId, "결제완료");
		cartService.updateCartState(cartStateUpdateDto);

		// 새로운 장바구니 생성
		CartDto newCartDto = new CartDto(userId);
		String newCartId = cartService.createNewCart(newCartDto);

		// 세션에 새로운 장바구니 ID 저장
		session.setAttribute("cart_id", newCartId);

		// 결제 성공 메시지를 보여주고 물건 리스트 페이지로 이동
		alertAndGo(response, "결제 성공!", "/sms/customermain");
	}

	public static void alertAndGo(HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('" + msg + "');location.href='" + url + "';</script>");
			w.flush();
			w.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/rollbackState", method = RequestMethod.POST)
	public String rollbackState(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String cartId = (String) session.getAttribute("cart_id");

		if (cartId != null && !cartId.isEmpty()) {
			// Cart 상태를 '장바구니'로 롤백합니다.
			CartDto cartStateUpdateDto = new CartDto(cartId, "장바구니");
			cartService.updateCartState(cartStateUpdateDto);
		}

		return "redirect:/cart/cart_itemList";
	}
}