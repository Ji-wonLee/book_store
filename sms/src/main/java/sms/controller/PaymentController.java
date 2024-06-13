package sms.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
	 * 기존의 cartService.listCartItems(userId) 메서드를 사용하여 장바구니 항목들을 가져온 후, 선택된 항목들만 필터링하는 방식으로 구현
	 * @param req
	 * @param model
	 * @return
	 */
	  @RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	    public String checkStock(HttpServletRequest req, ModelMap model) {
	        HttpSession session = req.getSession();
	        String userId = (String) session.getAttribute("user_id");

	        if (userId == null || userId.isEmpty()) {
	            model.addAttribute("error", "로그인이 필요합니다.");
	            return "login"; // 로그인 페이지로 리다이렉트
	        }

	        List<CartDto> cartItems = cartService.listCartItems(userId);

	        // 선택된 항목의 ID 목록을 가져옵니다.
	        String[] selectedItemIds = req.getParameterValues("cartItemIds");
	        if (selectedItemIds == null || selectedItemIds.length == 0) {
	            model.addAttribute("error", "선택된 상품이 없습니다.");
	            model.addAttribute("cartItems", cartItems);
	            return "cart/cart_itemList2";
	        }

	        // 선택된 항목 필터링 및 수량, 가격 설정
	        List<CartDto> selectedItems = new ArrayList<>();
	        for (CartDto item : cartItems) {
	            for (String itemId : selectedItemIds) {
	                if (item.getProduct_id().equals(itemId)) {
	                    int quantity = Integer.parseInt(req.getParameter("quantity_" + itemId));
	                    item.setQuantity(quantity); // 수량을 업데이트합니다.
	                    selectedItems.add(item);
	                    break;
	                }
	            }
	        }

	        boolean outOfStock = false;
	        for (CartDto item : selectedItems) {
	            int availableStock = cartService.getStock(item.getProduct_id());
	            if (availableStock < item.getQuantity()) {
	                outOfStock = true;
	                break;
	            }
	        }

	        if (outOfStock) {
	            model.addAttribute("error", "장바구니에 있는 일부 상품의 재고가 부족합니다.");
	            model.addAttribute("cartItems", cartItems);
	            return "cart/cart_itemList2";
	        }

	        String cartId = cartService.findCartId(userId);
	        String paymentId = paymentService.generatePaymentId(cartId);

	        PaymentDto paymentDto = new PaymentDto(userId, "", "", "", "", cartId);
	        paymentDto.setPayment_id(paymentId);
	        paymentService.savePaymentInfo(paymentDto);

	        CartDto cartStateUpdateDto = new CartDto(cartId, "결제중");
	        cartService.updateCartState(cartStateUpdateDto);

	        session.setAttribute("cart_id", cartId);
	        session.setAttribute("payment_id", paymentId);

	        for (CartDto item : selectedItems) {
	            cartService.updateStock(new Inventory(item.getProduct_id(), -item.getQuantity()));
	            PaymentDetailDto paymentDetailDto = new PaymentDetailDto(paymentId, item.getProduct_id(), item.getQuantity(), item.getProduct_price(), cartId);
	            paymentService.savePaymentDetail(paymentDetailDto);
	        }

	        model.addAttribute("cartItems", selectedItems);
	        model.addAttribute("user_id", userId);
	        model.addAttribute("payment_id", paymentId);

	        int totalPrice = selectedItems.stream().mapToInt(item -> item.getProduct_price() * item.getQuantity()).sum();
	        int totalQuantity = selectedItems.stream().mapToInt(CartDto::getQuantity).sum();

	        model.addAttribute("totalPrice", totalPrice);
	        model.addAttribute("totalQuantity", totalQuantity);

	        return "pay/payPage2";
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
	 * 결제 정보 입력을 처리합니다.
	 * @param req HttpServletRequest 객체
	 * @param receiverName 수령인 이름
	 * @param receiverAddress 수령인 주소
	 * @param model 모델 객체
	 * @return 금액 결제 페이지로 리다이렉트
	 */
    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    public String processPayment(HttpServletRequest req, @RequestParam("receiver_name") String receiverName,
                                 @RequestParam("receiver_address") String receiverAddress, ModelMap model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("user_id");
        String cartId = (String) session.getAttribute("cart_id");
        if (cartId == null) {
            model.addAttribute("error", "장바구니 ID를 찾을 수 없습니다.");
            return "cart/cart_itemList";
        }

        List<PaymentDetailDto> paymentItems = paymentService.getPaymentDetails(cartId);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUser_id(userId);
        paymentDto.setReceiver_name(receiverName);
        paymentDto.setReceiver_address(receiverAddress);

        session.setAttribute("receiver_name", receiverName);
        session.setAttribute("receiver_address", receiverAddress);

        String paymentId = (String) session.getAttribute("payment_id");
        paymentDto.setPayment_id(paymentId);

        

        model.addAttribute("paymentItems", paymentItems);
        model.addAttribute("user_id", userId);
        model.addAttribute("payment_id", paymentId);
        model.addAttribute("totalAmount", paymentItems.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum());
        return "pay/payInner2";
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