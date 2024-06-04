package sms.dao;

import sms.dto.SelectDto;

public interface SelectDao {
	
	public int addCart(SelectDto selectDto); //일단 SelectDto로 되어 있지만 Cart로 변경시켜 주세요!
	
}
