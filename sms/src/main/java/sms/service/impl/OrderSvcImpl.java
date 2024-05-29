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
	public int orderSave(Map<String, String> orderMap) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formatedNow = now.format(formatter);

		//order
		//totalprice 계산
		List<String> keyList =  new ArrayList<>(orderMap.keySet());
		int totalprice=0;
		for(String s : keyList) {
			String s_price = s.split("_")[1];
			int price = Integer.parseInt(s_price);
			int quantity = Integer.parseInt(orderMap.get(s));
			totalprice+=price*quantity;
		}

		//order 일련번호 생성
		String new_order_id = null;
		String org_order_id = orderDao.getOrderId();
		if(org_order_id==null) {
			new_order_id = "OD"+formatedNow+String.format("%02d", 1);
		}else {
			new_order_id = org_order_id.substring(0,6)+String.format("%02d", Integer.parseInt(org_order_id.substring(6))+1);			
		}
		orderDao.insertOrder(new Order(new_order_id,formatedNow, "writer",totalprice)); 

		//orderDetail
		for(String key : orderMap.keySet()) {
			String product_id=key.split("_")[0];
			int quantity=Integer.parseInt(orderMap.get(key));
			int price=Integer.parseInt(key.split("_")[1]);

			OrderDetail orderDetail = new OrderDetail(new_order_id,product_id ,quantity ,price );
			orderDao.insertOrderDetail(orderDetail);
		}

		//receive
		for(String key : orderMap.keySet()) {
			String receive_id="RC"+new_order_id.substring(2);
			 String order_id=new_order_id;
			 String receive_date=null;//DB에서 sysdate로
			String writer="writer1";
			String payer="writer2";
			int totalprice_r=totalprice;
			
		}
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