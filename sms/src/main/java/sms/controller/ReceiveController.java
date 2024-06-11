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

	//발주목록 출력
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String choiceOrder(ModelMap model,
			HttpServletRequest req) {	
		//order 선택하게 하기
		List<Order> orderIdList = receiveSvc.selectOrder();
		model.addAttribute("orderIdList", orderIdList);
		return "receive/orderList"; //-> 그 페이지 가서 상품들 선택
	}
	//선택한 발주와 매칭되는 입고서 출력
	@RequestMapping(value = "/updateReceive", method = RequestMethod.GET)
	public String updateReceive(@RequestParam String order_id,ModelMap model,
			HttpServletRequest req) {	
		// orderdetial 목록 model에 저장. 
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(order_id);
		model.addAttribute("rdList", rdList);
		return "receive/receivePage";
	}
	
	//입고서 수정 및 재고 반영
	@RequestMapping(value = "/toInventory", method = RequestMethod.GET)
	public String toInventory(@RequestParam String receive_id, @RequestParam String payer, @RequestParam String writer
			,@RequestParam Map<String, String> paramMap
			,ModelMap model, HttpServletRequest req) {	
		//System.out.println(paramMap);
		//입고서 수정//입고서 총액 수정
		//user_id가져오기
		HttpSession session=req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		model.addAttribute("user_id", user_id);
		//System.out.println(receive_id + " " + writer+" " + payer);
		
		receiveSvc.updateReceive(paramMap, receive_id, writer, payer);
		//재고 수정
		receiveSvc.receiveToInventory(paramMap);
		//발주서 state 변경
		receiveSvc.updateOrderState(receive_id);
		//수정확인된 화면으로
		List<ReceiveDetail> rdList = receiveSvc.selectReceiveDetail(receive_id);
		model.addAttribute("rdList", rdList);
		return "receive/endReceive";
	}
}












