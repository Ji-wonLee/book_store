package sms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.ProductDto;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;
import sms.dao.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<ProductDto> selectInventory() {
		List<ProductDto> listProduct = sqlSessionTemplate.selectList("order.selectInventory");
		return listProduct;
	}
	@Override
	public int insertOrder(Order order) {
		int count = sqlSessionTemplate.insert("order.insertOrder", order);
		return count;
	}
	@Override
	public int insertOrderDetail(OrderDetail orderDetail){
		int count = sqlSessionTemplate.insert("order.insertOrderDetail", orderDetail);
		return count;
	}
	@Override
	public int insertReceive(Receive receive) {
		int count = sqlSessionTemplate.insert("order.insertReceive", receive);
		return count;
	}
	@Override
	public int insertReceiveDetail(ReceiveDetail receiveDetail){
		int count = sqlSessionTemplate.insert("order.insertReceiveDetail", receiveDetail);
		return count;
	}
	@Override
	public String getOrderId() {
		String order_id = sqlSessionTemplate.selectOne("order.getOrderId");
		return order_id;
	}
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
}
