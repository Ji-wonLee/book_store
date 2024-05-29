package sms.service;

import java.util.List;
import java.util.Map;

import sms.dto.OrderDetail;
import sms.dto.ProductDto;

public interface OrderSvc {
	public	List<ProductDto> invenList();
	public int orderSave(Map<String, Integer> orderMap);
}
