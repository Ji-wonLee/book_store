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
import sms.dto.Receive;
import sms.dto.ReceiveDetail;
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
			 // 마지막 2자리를 추출
		    int lastTwoDigits = Integer.parseInt(org_order_id.substring(8, 10));
		    // 새로운 order_id 생성
		    new_order_id = org_order_id.substring(0, 8) + String.format("%02d", lastTwoDigits + 1);			
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
		
		System.out.println("receive");
		//receive
		String receive_id="RC"+new_order_id.substring(2);
		String order_id=new_order_id;
		String receive_date=null;//DB에서 sysdate로
		String writer="writer";
		String payer="payer";
		int totalprice_r=totalprice;

		Receive receive = new Receive(receive_id, order_id, receive_date, writer,payer,totalprice_r );
		orderDao.insertReceive(receive);
		System.out.println("receive2");
		
		//receiveDetail -> 보류,,, 일일이 넣을지 order에서 넣을지... 일단 전자로함
		for(String key : orderMap.keySet()) {
			String product_id=key.split("_")[0];
			int quantity=Integer.parseInt(orderMap.get(key));
			int price=Integer.parseInt(key.split("_")[1]);
			
			ReceiveDetail receiveDetail = new ReceiveDetail(receive_id, product_id, quantity, price);
			orderDao.insertReceiveDetail(receiveDetail);
		}
		
		return 0;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


}