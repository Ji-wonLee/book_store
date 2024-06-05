package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.SelectDao;
import sms.dto.CartDto;

@Repository
public class SelectDaoImpl implements SelectDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public String findCartId(String user_id) {
		String cartId = sqlSessionTemplate.selectOne("selectMapper/selectCart_id", user_id);
		return cartId;
	}
	
	@Override
	public int addCart(CartDto cartDto) {
		int addCart = sqlSessionTemplate.update("selectMapper/addCartProduct", cartDto);
		return addCart;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}