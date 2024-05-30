package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.ReceiveDetail;

public interface ReceiveSvc {
	public List<Order> selectOrder();
	public List<ReceiveDetail> selectReceiveDetail(String order_id);
	public int updateReceive(Map<String, Integer> receiveMap, String receive_id);
	public int receiveToInventory();
}
