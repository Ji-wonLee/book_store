package sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.OrderDao;
import sms.dto.OrderDetail;
import sms.dto.ProductDto;
import sms.service.OrderSvc;

@Service
public class OrderSvcImpl implements OrderSvc {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public	List<ProductDto> invenList(){
		List<ProductDto> listProduct = orderDao.selectInventory();
		return listProduct;
	}
	@Override
	public int orderSave(List<OrderDetail> list) {
		return 0;
	}
}
