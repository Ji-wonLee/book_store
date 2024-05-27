package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import sms.dao.cartDao;

public class cartDaoImpl implements cartDao {


	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	
}
