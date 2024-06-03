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
	        model.addAttribute("error", "Some items in your cart are out of stock.");
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
     * ���� ���¸� '�����Ϸ�'�� ������Ʈ�մϴ�.
     * @param cartStatusUpdateDto ������Ʈ�� ���� ����
     * @param model �� ��ü
     * @return ���� �Ϸ� �������� �����̷�Ʈ
     */
    @RequestMapping(value = "/updateStateToCompleted", method = RequestMethod.POST)
    public String updateCartStateToCompleted(CartDto cartStatusUpdateDto, ModelMap model) {
        cartStatusUpdateDto.setUser_id("DGo9fGM"); // �׽�Ʈ�� �ϵ��ڵ��� userId
        paymentService.updateCartStateToCompleted(cartStatusUpdateDto);
        return "redirect:/payment/payInner";
    }

    
//    /**
//     * ���� �ϷḦ ó���մϴ�.
//     * @param userId ����� ID
//     * @param payerName �Ա��� ��
//     * @param payerAccount �Ա��� ����
//     * @param model �� ��ü
//     * @return ���� �Ϸ� �������� �����̷�Ʈ
//     */
//    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
//    public String completePayment(@RequestParam("user_id") String userId, @RequestParam("payerName") String payerName,
//                                  @RequestParam("payerAccount") String payerAccount, ModelMap model) {
//        // ���� �Ϸ� ó�� ������ ���⿡ �߰��ϼ���.
//        model.addAttribute("message", "������ �Ϸ�Ǿ����ϴ�.");
//        return "pay/paymentSuccess";
//    }

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
     * ���� ���� Ȯ�� �������� �����ݴϴ�.
     * @param model �� ��ü
     * @return ���� ���� Ȯ�� JSP ������
     */
    @RequestMapping(value = "/payInner", method = RequestMethod.GET)
    public String showPayInner(ModelMap model) {
        String user_id = "DGo9fGM"; // �׽�Ʈ�� �ϵ��ڵ��� userId
        double totalAmount = calculateTotalAmount(user_id);
        model.addAttribute("totalAmount", totalAmount);
        return "payInner";
    }

//    /**
//     * ���� ��û�� ó���մϴ�.
//     * @param userId ����� ID
//     * @param receiverName ������ �̸�
//     * @param receiverAddress ������ �ּ�
//     * @param model �� ��ü
//     * @return ���� Ȯ�� �������� �����̷�Ʈ
//     */
//    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
//    public String processPayment(
//        @RequestParam("user_id") String userId,
//        @RequestParam("receiver_name") String receiverName,
//        @RequestParam("receiver_address") String receiverAddress,
//        ModelMap model) {
//
//        // ��ٱ��� ��� ��ȸ
//        List<CartDto> cartItems = cartService.listCartItems(userId);
//
//        // ��� Ȯ��
//        boolean outOfStock = false;
//        for (CartDto item : cartItems) {
//            int availableStock = cartService.getStock(item.getProduct_id());
//            if (availableStock < item.getQuantity()) {
//                outOfStock = true;
//                break;
//            }
//        }
//
//        // ��� ���� �� ���� ó��
//        if (outOfStock) {
//            model.addAttribute("error", "��� �����ϴ�.");
//            model.addAttribute("cartItems", cartItems);
//            return "pay/payPage";
//        }
//
//        // ���� ���� ���� �� ����
//        PaymentDto paymentDto = new PaymentDto(userId, receiverName, receiverAddress);
//        paymentService.savePaymentInfo(paymentDto);
//
//        // ���� ���� �������� �����̷�Ʈ
//        return "redirect:/payment/payInner";
//    }
    
//    /**
//     * ù��° ��Ʈ�ѷ� 
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
//        // ������ ���� �� ���� ó��
//        model.addAttribute("user_id", userId);  // ���� �������� user_id ����
//        return "forward:/paymentPage2"; // ���� �������� ����
//    }
//
//
//    /**
//     * �Ա��� ������ �޾� ������ �Ϸ��մϴ�.
//     * @param userId ����� ID
//     * @param payerName �Ա��� �̸�
//     * @param payerAccount �Ա��� ����
//     * @param model �� ��ü
//     * @return ���� �Ϸ� �������� �����̷�Ʈ
//     */
//    @PostMapping("/completePayment")
//    public String completePayment(
//        @RequestParam("user_id") String userId,
//        @RequestParam("payerName") String payerName,
//        @RequestParam("payerAccount") String payerAccount,
//        ModelMap model) {
//
//        // ���⿡ ���� ������ �����ϰ� �����ϴ� ������ �߰�
//        PaymentDto paymentDto = new PaymentDto(userId, payerName, payerAccount);
//        paymentService.savePaymentInfo(paymentDto);
//
//        // ���� ������ ���õ� �߰� ó�� ������ ����
//        // ��: ��ٱ��� ���� ������Ʈ, �ֹ� ��� ���� ��
//        cartService.updateCartStateToCompleted(userId); // ��ٱ��� ���¸� '���� �Ϸ�'�� ������Ʈ
//
//        // �𵨿� ���� �Ϸ� �޽��� �߰�
//        model.addAttribute("message", "������ ���������� �Ϸ�Ǿ����ϴ�.");
//
//        // ���� �Ϸ� �������� �����̷�Ʈ
//        return "redirect:/payment/paymentSuccess";
//    }


    
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
//    @RequestMapping(value = "/completePayment", method = RequestMethod.POST)
//    public String completePayment(
//        @RequestParam("payerName") String payerName,
//        @RequestParam("payerAccount") String payerAccount,
//        @ModelAttribute("paymentInfo") PaymentDto paymentInfo,
//        ModelMap model) {
//
//        // �Ա��� ���� �߰�
//        paymentInfo.setPayer_name(payerName);
//        paymentInfo.setPayer_account(payerAccount);
//
//        // ���� ���� ����
//        paymentService.savePaymentInfo(paymentInfo);
//
//        return "redirect:/payment/confirmationPage"; // ���� Ȯ�� ������ ���
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
            e.printStackTrace(); // ���� �� ��� �߰�
            model.addAttribute("error", "���� ó�� �� ������ �߻��߽��ϴ�: " + e.getMessage()); 
            return "/pay/errorPage";  
        }
    }

    @RequestMapping(value = "/paymentSuccess", method = RequestMethod.GET)
    public String paymentSuccess(ModelMap model) {
        model.addAttribute("message", "������ ���������� �Ϸ�Ǿ����ϴ�.");
        return "pay/paymentSuccess"; // ���� ���� ������ JSP
    }

    
    /**
     * ��ü ���� �ݾ��� ����մϴ�.
     * @param userId ����� ID
     * @return ��ü ���� �ݾ�
     */
    private double calculateTotalAmount(String user_id) {
        // ��ü ���� �ݾ� ��� ������ ����
        // ���÷� 0.0�� ��ȯ
        return 0.0;
    }
}
