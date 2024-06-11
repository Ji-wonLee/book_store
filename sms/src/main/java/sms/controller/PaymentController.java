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
	 * ��� Ȯ���ϰ� ���� �������� �̵��մϴ�.
	 * @param req HttpServletRequest ��ü
	 * @param model �� ��ü
	 * @return ���� ������ �Ǵ� ��� ���� ��� ������
	 */
	@RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	public String checkStock(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id"); // ���ǿ��� ����� ID�� �����ɴϴ�.

		// ������� ��ٱ��� �׸��� �����ɴϴ�.
		List<CartDto> cartItems = cartService.listCartItems(userId);
		boolean outOfStock = false;
		// �� �׸� ���� ��� Ȯ���մϴ�.
		for (CartDto item : cartItems) {
			int availableStock = cartService.getStock(item.getProduct_id());
			if (availableStock < item.getQuantity()) {
				outOfStock = true; // ��� ���� �� outOfStock�� true�� �����մϴ�.
				break;
			}
		}
		if (outOfStock) {
			// ��� �����ϸ� ���� �޽����� �Բ� ��ٱ��� �������� ���ư��ϴ�.
			model.addAttribute("error", "��ٱ��Ͽ� �ִ� �Ϻ� ��ǰ�� ��� �����մϴ�.");
			model.addAttribute("cartItems", cartItems);
			return "cart/cart_itemList";
		}

		// ��� ����ϸ� ���� ID�� �����մϴ�.
		String cartId = cartService.findCartId(userId);
		String paymentId = paymentService.generatePaymentId(cartId);

		// PaymentDto ���� �� ����
		PaymentDto paymentDto = new PaymentDto(userId, "", "", "", "", cartId);
		paymentDto.setPayment_id(paymentId);
		paymentService.savePaymentInfo(paymentDto);

		// Cart ���¸� '������'���� ������Ʈ�մϴ�.
		CartDto cartStateUpdateDto = new CartDto(cartId, "������");
		cartService.updateCartState(cartStateUpdateDto);

		// ���ǿ� cart_id�� payment_id ����
		session.setAttribute("cart_id", cartId);
		session.setAttribute("payment_id", paymentId);

		// ��� ������Ʈ: ���ŵ� ������ŭ ����� ���ݴϴ�.
		for (CartDto item : cartItems) {
			cartService.updateStock(new Inventory(item.getProduct_id(), -item.getQuantity()));
			// PaymentDetailDto ���� �� ����
			PaymentDetailDto paymentDetailDto = new PaymentDetailDto(paymentId, item.getProduct_id(), item.getQuantity(), item.getPrice());
			paymentService.savePaymentDetail(paymentDetailDto);
		}

		// �𵨿� �ʿ��� �Ӽ����� �߰��մϴ�.
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", userId);
		model.addAttribute("payment_id", paymentId); // ������ payment_id�� �𵨿� �߰��մϴ�.
		return "pay/payPage"; // ���� �������� �̵��մϴ�.
	}


	/**
	 * ���� ������ �����մϴ�.
	 * @param paymentDto ���� ����
	 * @param req HttpServletRequest ��ü
	 * @param model �� ��ü
	 * @return ���� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaymentInfo(PaymentDto paymentDto, HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		paymentDto.setUser_id(userId); 
		paymentService.savePaymentInfo(paymentDto);
		return "payment/payInner";
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
	 * ���� ����� �����մϴ�.
	 * @param paymentDto ���� ��� ����
	 * @param req HttpServletRequest ��ü
	 * @param model �� ��ü
	 * @return ���� �������� �����̷�Ʈ
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
	 * ���� ��ϰ� ���ο� ��ٱ��ϸ� �����մϴ�.
	 * @param paymentDto ���� ��� ����
	 * @param req HttpServletRequest ��ü
	 * @param model �� ��ü
	 * @return ���� �Ϸ� �������� �����̷�Ʈ
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
	 * ���� ���� �������� �����ݴϴ�.
	 * @param req HttpServletRequest ��ü
	 * @param model �� ��ü
	 * @return ���� ���� JSP ������
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
	 * ���� ���� �Է��� ó���մϴ�.
	 * @param req HttpServletRequest ��ü
	 * @param receiverName ������ �̸�
	 * @param receiverAddress ������ �ּ�
	 * @param model �� ��ü
	 * @return �ݾ� ���� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST)
	public String processPayment(
			HttpServletRequest req,
			@RequestParam("receiver_name") String receiverName,
			@RequestParam("receiver_address") String receiverAddress,
			ModelMap model) {

		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");

		// �ӽ� DTO�� ������ ���� ����
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setUser_id(userId);
		paymentDto.setReceiver_name(receiverName);
		paymentDto.setReceiver_address(receiverAddress);

		// ���ǿ� ������ ���� ����
		session.setAttribute("receiver_name", receiverName);
		session.setAttribute("receiver_address", receiverAddress);

		// �𵨿� DTO ����
		model.addAttribute("paymentInfo", paymentDto);

		return "pay/payInner";
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