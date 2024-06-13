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
	 * ������ cartService.listCartItems(userId) �޼��带 ����Ͽ� ��ٱ��� �׸���� ������ ��, ���õ� �׸�鸸 ���͸��ϴ� ������� ����
	 * @param req
	 * @param model
	 * @return
	 */
	  @RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	    public String checkStock(HttpServletRequest req, ModelMap model) {
	        HttpSession session = req.getSession();
	        String userId = (String) session.getAttribute("user_id");

	        if (userId == null || userId.isEmpty()) {
	            model.addAttribute("error", "�α����� �ʿ��մϴ�.");
	            return "login"; // �α��� �������� �����̷�Ʈ
	        }

	        List<CartDto> cartItems = cartService.listCartItems(userId);

	        // ���õ� �׸��� ID ����� �����ɴϴ�.
	        String[] selectedItemIds = req.getParameterValues("cartItemIds");
	        if (selectedItemIds == null || selectedItemIds.length == 0) {
	            model.addAttribute("error", "���õ� ��ǰ�� �����ϴ�.");
	            model.addAttribute("cartItems", cartItems);
	            return "cart/cart_itemList2";
	        }

	        // ���õ� �׸� ���͸� �� ����, ���� ����
	        List<CartDto> selectedItems = new ArrayList<>();
	        for (CartDto item : cartItems) {
	            for (String itemId : selectedItemIds) {
	                if (item.getProduct_id().equals(itemId)) {
	                    int quantity = Integer.parseInt(req.getParameter("quantity_" + itemId));
	                    item.setQuantity(quantity); // ������ ������Ʈ�մϴ�.
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
	            model.addAttribute("error", "��ٱ��Ͽ� �ִ� �Ϻ� ��ǰ�� ��� �����մϴ�.");
	            model.addAttribute("cartItems", cartItems);
	            return "cart/cart_itemList2";
	        }

	        String cartId = cartService.findCartId(userId);
	        String paymentId = paymentService.generatePaymentId(cartId);

	        PaymentDto paymentDto = new PaymentDto(userId, "", "", "", "", cartId);
	        paymentDto.setPayment_id(paymentId);
	        paymentService.savePaymentInfo(paymentDto);

	        CartDto cartStateUpdateDto = new CartDto(cartId, "������");
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




	// ��ٱ��� ���� �ڷ� ����
	@PostMapping("/revertPayment")
	public void revertPayment(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String cartId = (String) session.getAttribute("cart_id");
		if (cartId != null) {
			cartService.revertCartState(cartId);
		}
	}


	/**
	 * ���� ���� �Է��� ó���մϴ�.
	 * @param req HttpServletRequest ��ü
	 * @param receiverName ������ �̸�
	 * @param receiverAddress ������ �ּ�
	 * @param model �� ��ü
	 * @return �ݾ� ���� �������� �����̷�Ʈ
	 */
    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    public String processPayment(HttpServletRequest req, @RequestParam("receiver_name") String receiverName,
                                 @RequestParam("receiver_address") String receiverAddress, ModelMap model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("user_id");
        String cartId = (String) session.getAttribute("cart_id");
        if (cartId == null) {
            model.addAttribute("error", "��ٱ��� ID�� ã�� �� �����ϴ�.");
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
		return "product/productMain"; // �� ������ ������ ���� /WEB-INF/views/product/productMain.jsp�� ���ε�
	}

	/**
	 * ���� �Ϸ� ó��
	 * @param req HttpServletRequest ��ü
	 * @param payerName �Ա��� �̸�
	 * @param payerAccount �Ա��� ����
	 * @param response HttpServletResponse ��ü
	 * @return �����̷��� URL
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

		// �α��� ���� ���� ���� Ȯ��
		System.out.println("Session user_id: " + userId);
		System.out.println("Session cart_id: " + cartId);
		System.out.println("Session payment_id: " + paymentId);

		if (cartId == null || cartId.isEmpty()) {
			throw new IllegalArgumentException("Invalid cartId");
		}

		// PaymentDto ���� �� ���� ���� ������Ʈ
		PaymentDto paymentDto = new PaymentDto(paymentId, userId, receiverName, receiverAddress, payerName, payerAccount, cartId);
		System.out.println("PaymentDto: " + paymentDto.toString()); // ������ �α� �߰�
		paymentService.updatePaymentInfo(paymentDto);

		// ��ٱ��� ���¸� '�����Ϸ�'�� ������Ʈ
		CartDto cartStateUpdateDto = new CartDto(cartId, "�����Ϸ�");
		cartService.updateCartState(cartStateUpdateDto);

		// ���ο� ��ٱ��� ����
		CartDto newCartDto = new CartDto(userId);
		String newCartId = cartService.createNewCart(newCartDto);

		// ���ǿ� ���ο� ��ٱ��� ID ����
		session.setAttribute("cart_id", newCartId);

		// ���� ���� �޽����� �����ְ� ���� ����Ʈ �������� �̵�
		alertAndGo(response, "���� ����!", "/sms/customermain");
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
			// Cart ���¸� '��ٱ���'�� �ѹ��մϴ�.
			CartDto cartStateUpdateDto = new CartDto(cartId, "��ٱ���");
			cartService.updateCartState(cartStateUpdateDto);
		}

		return "redirect:/cart/cart_itemList";
	}
}