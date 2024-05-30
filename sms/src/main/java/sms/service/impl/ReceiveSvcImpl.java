package sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.OrderDao;
import sms.dao.ReceiveDao;
import sms.dto.Order;
import sms.dto.OrderDetail;
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
	public int updateReceive(Map<String, Integer> receiveMap, String receive_id) {
		//receiveDetail ¼öÁ¤
		
		return 0;
	}

	@Override
	public int receiveToInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ReceiveDao getReceiveDao() {
		return receiveDao;
	}

	public void setReceiveDao(ReceiveDao receiveDao) {
		this.receiveDao = receiveDao;
	}

}
