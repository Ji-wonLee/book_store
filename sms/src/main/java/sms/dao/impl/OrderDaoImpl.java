package sms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dto.ProductDto;
import sms.dao.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<ProductDto> selectInventory() {
		List<ProductDto> listProduct = sqlSessionTemplate.selectList(null);
		return listProduct;
	}
	@Override
	public int insertOrder() {
		return 0;
	}
	@Override
	public int insertReceiveDetail(){
		return 0;
	}
	@Override
	public int receive() {
		return 0;
	}
	@Override
	public int insertOrderDetail(){
		return 0;
	}
}
