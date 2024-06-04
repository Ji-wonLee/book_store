package sms.service.impl;

import org.springframework.stereotype.Service;

import sms.dao.SelectDao;
import sms.dto.SelectDto;
import sms.service.SelectSvc;

@Service
public class SelectSvcImpl implements SelectSvc{
	
	private SelectDao selectDao;

	@Override
	public SelectDto addCart(SelectDto selectDto) {
		return null;
	}

	public SelectDao getSelectDao() {
		return selectDao;
	}

	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
}