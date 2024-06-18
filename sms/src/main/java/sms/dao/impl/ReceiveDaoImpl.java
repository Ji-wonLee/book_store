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

	@Override
	public int updateOrderState(String order_id) {
		sqlSessionTemplate.update("receive.updateOrderState",order_id);
		return 0;
	}
	@Override
	public int updateWriter(Receive receive) {
		sqlSessionTemplate.update("receive.updateWriter",receive);
		return 0;
	}

	@Override
	public String selectState(String order_id) {
		String state = sqlSessionTemplate.selectOne("order.selectState", order_id);
		return state;
	}
	@Override
	public String selectPayer(String receive_id) {
		String payer = sqlSessionTemplate.selectOne("receive.selectPayer", receive_id);
		return payer;
	}

	@Override
	public String selectWriter(String receive_id) {
		String writer = sqlSessionTemplate.selectOne("receive.selectWriter", receive_id);
		return writer;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}







}
