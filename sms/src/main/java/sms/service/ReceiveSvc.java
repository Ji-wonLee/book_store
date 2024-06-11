package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.ReceiveDetail;

public interface ReceiveSvc {
	public List<Order> selectOrder();
	public List<ReceiveDetail> selectReceiveDetail(String order_id);
	public int updateReceive(Map<String, String> receiveMap, String receive_id,String user_id);
	public int receiveToInventory(Map<String, String> receiveMap);
	public int updateOrderState(String receive_id);
}
