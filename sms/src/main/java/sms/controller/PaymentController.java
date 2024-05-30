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
     * ���� ������ �����մϴ�.
     * @param paymentDto ���� ����
     * @param model �� ��ü
     * @return ���� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePaymentInfo(PaymentDto paymentDto, ModelMap model) {
        paymentDto.setUser_id("defaultUserId"); // �׽�Ʈ�� �ϵ��ڵ��� userId
        paymentService.savePaymentInfo(paymentDto);
        return "redirect:/payment/payPage";
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
    public String showPayPage(ModelMap model) {
        String userId = "defaultUserId"; // �׽�Ʈ�� �ϵ��ڵ��� userId
        List<CartDto> cartItems = cartService.listCartItems(userId);
        model.addAttribute("cartItems", cartItems);
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
