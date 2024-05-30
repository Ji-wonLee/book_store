package sms.dao;

import java.util.List;
import sms.dto.ProductDto;

public interface OrderDao {
	public List<ProductDto> selectInventory();
	public int insertOrder();
	public int insertReceiveDetail();
	public int receive();
	public int insertOrderDetail();
}
