package sms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.OrderDao;
import sms.dto.Order;
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
	public int orderSave(Map<String, Integer> orderMap) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formatedNow = now.format(formatter);

		//order
		//totalprice 계산
		List<String> keyList =  new ArrayList<>(orderMap.keySet());
		int totalprice=0;
		for(String s : keyList) {
			totalprice+=Integer.parseInt(s.split("_")[1])*orderMap.get(s);
		}
		System.out.println(totalprice);
		//order 일련번호 생성
		String new_order_id = null;
		String order_id = orderDao.getOrderId();
		if(order_id==null) {
			new_order_id = "OD"+formatedNow+String.format("%02d", 1);
		}else {
			new_order_id = order_id.substring(0,6)+String.format("%02d", Integer.parseInt(order_id.substring(6))+1);			
		}
		orderDao.insertOrder(new Order(new_order_id,formatedNow, "writer",totalprice)); 

		//orderDetail

		//receive

		//receiveDetail
		return 0;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


}