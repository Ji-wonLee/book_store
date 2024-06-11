package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.OrderDetail;
import sms.dto.OrderSearchDto;
import sms.dto.ProductDto;

public interface OrderSvc {
	public List<ProductDto> invenList();
	public List<ProductDto> orderSearch(OrderSearchDto orderSearchDto);
	public int orderSave(Map<String, String> orderMap, String user_id);
}
