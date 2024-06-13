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
	//전체 발주 목록 
	public List<Order> selectOrder();
	//state 검색한 발주 목록
	public List<Order> orderStateSearch(String state);
	//발주 상세 목록
	public List<OrderDetail> selectOrderDetail(String order_id);
	//전체상품 출력
	public List<ProductDto> selectInventory();
	//각 테이블에 저장
	public int insertOrder(Order order);
	public int insertOrderDetail(OrderDetail orderDetail);
	public int insertReceive(Receive receive);
	public int insertReceiveDetail(ReceiveDetail receiveDetail);
	//오늘 날짜중 제일 마지막 order_id 가져오기
	public String getOrderId();

	// 입력창에 글자를 검색해서 객체 list를 찾아옴, 해당 문자를 객체ID 또는 객체 명과 비교
	List<ProductDto> productSearchWithText(OrderSearchDto OrderSearchDto);

	// 분류를 검색하여 객체 list를 찾아옴
	List<ProductDto> productSearchWithCategory(OrderSearchDto OrderSearchDto);

	// 분류와 글자를 입력 받아서 객체 리스트를 찾아옴, 문자를 ID, 객체 명과 비교
	List<ProductDto> productSearchDual(OrderSearchDto orderSearchDto);
}
