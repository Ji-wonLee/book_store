package sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.dao.SelectDao;
import sms.dto.CartDto;
import sms.service.SelectSvc;

@Service
public class SelectSvcImpl implements SelectSvc{
	
	@Autowired
	private SelectDao selectDao;

	@Override
	public String findCartId(String user_id) {
		String cart_id = selectDao.findCartId(user_id);
		return cart_id;
	}

	@Override
	public int addCart(CartDto cartDto) {
		int insertNum = selectDao.addCart(cartDto);
		return insertNum;
	}
	
	public SelectDao getSelectDao() {
		return selectDao;
	}

	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
}