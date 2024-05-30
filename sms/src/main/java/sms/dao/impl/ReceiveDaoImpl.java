package sms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.ReceiveDao;
import sms.dto.Inventory;
import sms.dto.Order;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;
@Repository
public class ReceiveDaoImpl implements ReceiveDao{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List<Order> selectOrder() {
		List<Order> orderList = sqlSessionTemplate.selectList("receive.selectOrder");
		return orderList;
	}

	@Override
	public List<ReceiveDetail> selectReceiveDetail(String receive_id) {
		List<ReceiveDetail> rdList = sqlSessionTemplate.selectList("receive.selectReceiveDetail", receive_id);
		return rdList;
	}

	@Override
	public int updateReceive(String receive_id) {
		sqlSessionTemplate.update("receive.updateReceive", receive_id);
		return 0;
	}

	@Override
	public int updateReceiveDetail(ReceiveDetail receiveDetail) {
		sqlSessionTemplate.update("receive.updateReceiveDetail", receiveDetail);
		return 0;
	}

	@Override
	public int receiveToInventory(Inventory inventory) {
		sqlSessionTemplate.update("receive.receiveToInventory", inventory);
		return 0;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
