package sms.controller;

import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public String choiceOrder(ModelMap model) {	
		//order �����ϰ� �ϱ�
		System.out.println("choiceOrder - controller");
		List<Order> orderIdList = receiveSvc.selectOrder();
		model.addAttribute("orderIdList", orderIdList);
		return "receive/orderList"; //-> �� ������ ���� ��ǰ�� ����
	}
	
	@RequestMapping(value = "/updateReceive", method = RequestMethod.POST)
	public String updateReceive(@RequestParam String order_id,ModelMap model) {	
		// orderdetial ��� model�� ����. 
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(order_id);
		model.addAttribute("rdList", rdList);
		return "receive/receivePage";
	}
	
	@RequestMapping(value = "/toInventory", method = RequestMethod.POST)
	public String toInventory(@RequestParam String receive_id,@RequestParam Map<String, String> paramMap, ModelMap model) {	
		System.out.println(paramMap);
		//�԰� ����//�԰� �Ѿ� ����
		receiveSvc.updateReceive(paramMap, receive_id);
		//��� ����
		receiveSvc.receiveToInventory(paramMap);
		return "receive/receivePage";
	}
}












