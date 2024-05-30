package sms.dao;

import java.util.List;

import sms.dto.Order;
import sms.dto.ReceiveDetail;

public interface ReceiveDao {
	public List<Order> selectOrder();
	public List<ReceiveDetail> selectReceiveDetail(String receive_id);
	public int updateReceive();
	public int updateReceiveDetail();
	public int receiveToInventory();
	
}
