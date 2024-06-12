package sms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sms.dao.ReceiveDao;
import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.ReceiveDetail;
import sms.service.ReceiveSvc;

@Controller
public class ReceiveController {
	@Autowired
	private ReceiveSvc receiveSvc;

	//���ָ�� ���
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String choiceOrder(ModelMap model,
			HttpServletRequest req) {	
		//order �����ϰ� �ϱ�
		List<Order> orderIdList = receiveSvc.selectOrder();
		model.addAttribute("orderIdList", orderIdList);
		return "receive/orderList"; //-> �� ������ ���� ��ǰ�� ����
	}
	//������ ���ֿ� ��Ī�Ǵ� �԰� ���
	@RequestMapping(value = "/updateReceive", method = RequestMethod.GET)
	public String updateReceive(@RequestParam String order_id,ModelMap model,
			HttpServletRequest req) {	
		// orderdetial ��� model�� ����. 
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(order_id);
		model.addAttribute("rdList", rdList);
		return "receive/receivePage";
	}
	
	//�԰� ���� �� ��� �ݿ�
	@RequestMapping(value = "/toInventory", method = RequestMethod.GET)
	public String toInventory(@RequestParam String receive_id, @RequestParam String payer, @RequestParam String writer
			,@RequestParam Map<String, String> paramMap
			,ModelMap model, HttpServletRequest req) {	
		//System.out.println(paramMap);
		//�԰� ����//�԰� �Ѿ� ����
		//user_id��������
		HttpSession session=req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", user_id);
		//System.out.println(receive_id + " " + writer+" " + payer);
		
		receiveSvc.updateReceive(paramMap, receive_id, writer, payer);
		//��� ����
		receiveSvc.receiveToInventory(paramMap);
		//���ּ� state ����
		receiveSvc.updateOrderState(receive_id);
		//����Ȯ�ε� ȭ������
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(receive_id);
		model.addAttribute("rdList", rdList);
		return "receive/endReceive";
	}
}












