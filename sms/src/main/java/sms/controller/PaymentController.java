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
	 * ��� Ȯ���ϰ� ���� �������� �̵��մϴ�.
	 * @param userId ����� ID
	 * @param model �� ��ü
	 * @return ���� ������ �Ǵ� ��� ���� ��� ������
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
			model.addAttribute("error", "��ٱ��Ͽ� �ִ� �Ϻ� ��ǰ�� ��� �����մϴ�.");
			model.addAttribute("cartItems", cartItems);
			return "cart/cart_itemList";
		}
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", user_id1);
		return "pay/payPage";
	}
	/**
	 * ���� ������ �����մϴ�.
	 * @param paymentDto ���� ����
	 * @param model �� ��ü
	 * @return ���� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaymentInfo(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // �׽�Ʈ�� �ϵ��ڵ��� userId
		paymentService.savePaymentInfo(paymentDto);
		return "payment/payInner";
	}



	/**
	 * ���� ����� �����մϴ�.
	 * @param paymentDto ���� ��� ����
	 * @param model �� ��ü
	 * @return ���� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPaymentRecord(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // �׽�Ʈ�� �ϵ��ڵ��� userId
		paymentService.createPaymentRecord(paymentDto);
		return "redirect:/payment/payPage";
	}

	/**
	 * ���� ��ϰ� ���ο� ��ٱ��ϸ� �����մϴ�.
	 * @param paymentDto ���� ��� ����
	 * @param model �� ��ü
	 * @return ���� �Ϸ� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/createWithNewCart", method = RequestMethod.POST)
	public String createPaymentRecordAndNewCart(PaymentDto paymentDto, ModelMap model) {
		paymentDto.setUser_id("DGo9fGM"); // �׽�Ʈ�� �ϵ��ڵ��� userId
		paymentService.createPaymentRecordAndNewCart(paymentDto);
		return "redirect:/payment/payInner";
	}

	/**
	 * ���� ���� �������� �����ݴϴ�.
	 * @param model �� ��ü
	 * @return ���� ���� JSP ������
	 */
	@RequestMapping(value = "/payPage", method = RequestMethod.GET)
	public String showPayPage(@RequestParam("user_id") String user_id, ModelMap model) {
		List<CartDto> cartItems = cartService.listCartItems(user_id);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("user_id", user_id);
		return "pay/payPage";
	}



	/**
	 * ���� ���� �Է��� ó���մϴ�.
	 * @param userId ����� ID
	 * @param receiverName ������ �̸�
	 * @param receiverAddress ������ �ּ�
	 * @param model �� ��ü
	 * @return �ݾ� ���� �������� �����̷�Ʈ
	 */
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST)
	public String processPayment(
			@RequestParam("user_id") String userId,
			@RequestParam("receiver_name") String receiverName,
			@RequestParam("receiver_address") String receiverAddress,
			ModelMap model) {

		// �ӽ� DTO�� ������ ���� ����
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setUser_id(userId);
		paymentDto.setReceiver_name(receiverName);
		paymentDto.setReceiver_address(receiverAddress);

		// �𵨿� DTO ����
		model.addAttribute("paymentInfo", paymentDto);

		return "/pay/payInner";
	}

	/**
	 * �ݾ� ���� ������ �Է¹ް� ������ �Ϸ��մϴ�.
	 * @param payerName �Ա��� �̸�
	 * @param payerAccount �Ա��� ����
	 * @param paymentInfo ���� �ܰ迡�� ����� ���� ����
	 * @param model �� ��ü
	 * @return ���� �Ϸ� �������� �����̷�Ʈ
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

				
		// ���� ���� ���� ���� ȣ��
		PaymentDto paymentDto = new PaymentDto(userId, receiverName, receiverAddress, payerName, payerAccount, cartId);

		// ���� �Ϸ� �� ���ο� ��ٱ��� ���� �� ���� ������Ʈ
		cartService.completePaymentAndCreateNewCart(paymentDto);

		// ���� ���� �� ���� ������Ʈ
		CartDto cartStatusUpdateDto = new CartDto();
		cartStatusUpdateDto.setUser_id(userId);
		cartStatusUpdateDto.setState("�����Ϸ�");
		cartService.updateCartState(cartStatusUpdateDto);

		return "/pay/paymentSuccess";

	}


}