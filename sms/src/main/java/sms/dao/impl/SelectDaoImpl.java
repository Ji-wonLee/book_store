package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.SelectDao;
import sms.dto.SelectDto;

@Repository
public class SelectDaoImpl implements SelectDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int addCart(SelectDto selectDto) { //Insert, SelectDto는 나중에 CartDto와 합쳐야됩니다.
		int addCart = sqlSessionTemplate.update("SelectMapper/addCartProduct", selectDto);
		return addCart;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}