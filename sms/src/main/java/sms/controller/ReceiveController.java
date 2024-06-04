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
	
	//���ָ�� ���
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String choiceOrder(ModelMap model) {	
		//order �����ϰ� �ϱ�
		List<Order> orderIdList = receiveSvc.selectOrder();
		model.addAttribute("orderIdList", orderIdList);
		return "receive/orderList"; //-> �� ������ ���� ��ǰ�� ����
	}
	//������ ���ֿ� ��Ī�Ǵ� �԰� ���
	@RequestMapping(value = "/updateReceive", method = RequestMethod.GET)
	public String updateReceive(@RequestParam String order_id,ModelMap model) {	
		// orderdetial ��� model�� ����. 
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(order_id);
		model.addAttribute("rdList", rdList);
		return "receive/receivePage";
	}
	//�԰� ���� �� ��� �ݿ�
	@RequestMapping(value = "/toInventory", method = RequestMethod.GET)
	public String toInventory(@RequestParam String receive_id,@RequestParam Map<String, String> paramMap, ModelMap model) {	
		//System.out.println(paramMap);
		//�԰� ����//�԰� �Ѿ� ����
		receiveSvc.updateReceive(paramMap, receive_id);
		//��� ����
		receiveSvc.receiveToInventory(paramMap);
		//����Ȯ�ε� ȭ������
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(receive_id);
		model.addAttribute("rdList", rdList);
		return "receive/endReceive";
	}
}












