package sms.dao;

import java.util.List;

import sms.dto.Inventory;
import sms.dto.Order;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;

public interface ReceiveDao {
	public List<Order> selectOrder();
	public List<ReceiveDetail> selectReceiveDetail(String receive_id);
	public int updateReceive(String receive_id);
	public int updateReceiveDetail(ReceiveDetail receiveDetail);
	public int receiveToInventory(Inventory inventory);
	//orderSatate 입고완료로 변경
	public int updateOrderState(String order_id);
	//writer 작성
	public int updateWriter(Receive receive);
	public String selectState(String order_id);
	public String selectPayer(String receive_id);
	public String selectWriter(String receive_id);
	
}
