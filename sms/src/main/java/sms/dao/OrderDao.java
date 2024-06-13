package sms.dao;

import java.util.List;

import sms.dto.Order;
import sms.dto.OrderDetail;
import sms.dto.OrderSearchDto;
import sms.dto.ProductDto;
import sms.dto.Receive;
import sms.dto.ReceiveDetail;
import sms.dto.SearchDto;

public interface OrderDao {
	//��ü ���� ��� 
	public List<Order> selectOrder();
	//state �˻��� ���� ���
	public List<Order> orderStateSearch(String state);
	//���� �� ���
	public List<OrderDetail> selectOrderDetail(String order_id);
	//��ü��ǰ ���
	public List<ProductDto> selectInventory();
	//�� ���̺� ����
	public int insertOrder(Order order);
	public int insertOrderDetail(OrderDetail orderDetail);
	public int insertReceive(Receive receive);
	public int insertReceiveDetail(ReceiveDetail receiveDetail);
	//���� ��¥�� ���� ������ order_id ��������
	public String getOrderId();

	// �Է�â�� ���ڸ� �˻��ؼ� ��ü list�� ã�ƿ�, �ش� ���ڸ� ��üID �Ǵ� ��ü ��� ��
	List<ProductDto> productSearchWithText(OrderSearchDto OrderSearchDto);

	// �з��� �˻��Ͽ� ��ü list�� ã�ƿ�
	List<ProductDto> productSearchWithCategory(OrderSearchDto OrderSearchDto);

	// �з��� ���ڸ� �Է� �޾Ƽ� ��ü ����Ʈ�� ã�ƿ�, ���ڸ� ID, ��ü ��� ��
	List<ProductDto> productSearchDual(OrderSearchDto orderSearchDto);
}
