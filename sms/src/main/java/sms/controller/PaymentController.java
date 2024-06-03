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
     * ��� Ȯ���ϰ� ���� �������� �̵��մϴ�.
     * @param userId ����� ID
     * @param model �� ��ü
     * @return ���� ������ �Ǵ� ��� ���� ��� ������
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
     * ���� ������ �����մϴ�.
     * @param paymentDto ���� ����
     * @param model �� ��ü
     * @return ���� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePaymentInfo(PaymentDto paymentDto, ModelMap model) {
        paymentDto.setUser_id("defaultUserId"); // �׽�Ʈ�� �ϵ��ڵ��� userId
        paymentService.savePaymentInfo(paymentDto);
        return "payment/payInner";
    }

    /**
     * ���� ���¸� '�����Ϸ�'�� ������Ʈ�մϴ�.
     * @param cartStatusUpdateDto ������Ʈ�� ���� ����
     * @param model �� ��ü
     * @return ���� �Ϸ� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/updateStateToCompleted", method = RequestMethod.POST)
    public String updateCartStateToCompleted(CartDto cartStatusUpdateDto, ModelMap model) {
        cartStatusUpdateDto.setUser_id("defaultUserId"); // �׽�Ʈ�� �ϵ��ڵ��� userId
        paymentService.updateCartStateToCompleted(cartStatusUpdateDto);
        return "redirect:/payment/payInner";
    }

    
    /**
     * ���� �ϷḦ ó���մϴ�.
     * @param userId ����� ID
     * @param payerName �Ա��� ��
     * @param payerAccount �Ա��� ����
     * @param model �� ��ü
     * @return ���� �Ϸ� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
    public String completePayment(@RequestParam("user_id") String userId, @RequestParam("payerName") String payerName,
                                  @RequestParam("payerAccount") String payerAccount, ModelMap model) {
        // ���� �Ϸ� ó�� ������ ���⿡ �߰��ϼ���.
        model.addAttribute("message", "������ �Ϸ�Ǿ����ϴ�.");
        return "pay/paymentSuccess";
    }

    /**
     * ���� ����� �����մϴ�.
     * @param paymentDto ���� ��� ����
     * @param model �� ��ü
     * @return ���� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPaymentRecord(PaymentDto paymentDto, ModelMap model) {
        paymentDto.setUser_id("defaultUserId"); // �׽�Ʈ�� �ϵ��ڵ��� userId
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
        paymentDto.setUser_id("defaultUserId"); // �׽�Ʈ�� �ϵ��ڵ��� userId
        paymentService.createPaymentRecordAndNewCart(paymentDto);
        return "redirect:/payment/payInner";
    }

    /**
     * ���� ���� �������� �����ݴϴ�.
     * @param model �� ��ü
     * @return ���� ���� JSP ������
     */
    @RequestMapping(value = "/payPage", method = RequestMethod.GET)
    public String showPayPage(@RequestParam("userId") String userId, ModelMap model) {
        List<CartDto> cartItems = cartService.listCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("userId", userId);
        return "pay/payPage";
    }

    /**
     * ���� ���� Ȯ�� �������� �����ݴϴ�.
     * @param model �� ��ü
     * @return ���� ���� Ȯ�� JSP ������
     */
    @RequestMapping(value = "/payInner", method = RequestMethod.GET)
    public String showPayInner(ModelMap model) {
        String userId = "defaultUserId"; // �׽�Ʈ�� �ϵ��ڵ��� userId
        double totalAmount = calculateTotalAmount(userId);
        model.addAttribute("totalAmount", totalAmount);
        return "payInner";
    }

    /**
     * ���� ��û�� ó���մϴ�.
     * @param paymentDto ���� ����
     * @param model �� ��ü
     * @return ���� Ȯ�� �������� �����̷�Ʈ
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
     * ��ü ���� �ݾ��� ����մϴ�.
     * @param userId ����� ID
     * @return ��ü ���� �ݾ�
     */
    private double calculateTotalAmount(String userId) {
        // ��ü ���� �ݾ� ��� ������ ����
        // ���÷� 0.0�� ��ȯ
        return 0.0;
    }
}