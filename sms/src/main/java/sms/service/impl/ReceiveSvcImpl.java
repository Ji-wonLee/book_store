package sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.OrderDao;
import sms.dao.ReceiveDao;
import sms.dto.Inventory;
import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;
import sms.service.ReceiveSvc;
@Service
public class ReceiveSvcImpl implements ReceiveSvc{
	@Autowired
	private ReceiveDao receiveDao;
	@Override
	public List<Order> selectOrder() {
		List<Order> orderIdList = receiveDao.selectOrder();
		return orderIdList;
	}

	@Override
	public List<ReceiveDetail> selectReceiveDetail(String order_id) {
		String receive_id = "RC"+order_id.substring(2);
		List<ReceiveDetail> rdList = receiveDao.selectReceiveDetail(receive_id);
		return rdList;
	}

	@Override
	public int updateReceive(Map<String, String> receiveMap, String receive_id) {
		//receiveDetail 수정
		for(String key : receiveMap.keySet()) {
			if(!key.equals("receive_id")) {
				//System.out.println(key +"/"+receiveMap.get(key));
				int test = Integer.parseInt( receiveMap.get(key));
				ReceiveDetail receiveDetail = new ReceiveDetail(receive_id, key,test, 0);
				receiveDao.updateReceiveDetail(receiveDetail);
			}
		}
		//receive 총액 수정
		receiveDao.updateReceive(receive_id);
		return 0;
	}

	@Override
	public int receiveToInventory(Map<String, String> receiveMap) {
		// 재고수정
		for(String product_id: receiveMap.keySet()) {
			if(!product_id.equals("receive_id")) {
				int test = Integer.parseInt(receiveMap.get(product_id));
				Inventory inventory = new Inventory(product_id,test);
				receiveDao.receiveToInventory(inventory);
			}
		}
		return 0;
	}

	public ReceiveDao getReceiveDao() {
		return receiveDao;
	}

	public void setReceiveDao(ReceiveDao receiveDao) {
		this.receiveDao = receiveDao;
	}

}
