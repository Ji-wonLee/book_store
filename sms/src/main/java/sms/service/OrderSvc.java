package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.OrderSearchDto;
import sms.dto.ProductDto;

public interface OrderSvc {
	public List<Order> selectOrder();
	public List<Order> orderStateSearch(String state);
	public List<OrderDetail> selectOrderDetail(String order_id);
	public List<ProductDto> invenList();
	public List<ProductDto> orderSearch(OrderSearchDto orderSearchDto);
	public int orderSave(Map<String, String> orderMap, String user_id);
}
