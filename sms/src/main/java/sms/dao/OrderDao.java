package sms.dao;

import java.util.List;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.ProductDto;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;

public interface OrderDao {
	public List<ProductDto> selectInventory();
	public int insertOrder(Order order);
	public int insertOrderDetail(OrderDetail orderDetail);
	public int receive(Receive receive);
	public int insertReceiveDetail(ReceiveDetail receiveDetail);
	public String getOrderId();
}
