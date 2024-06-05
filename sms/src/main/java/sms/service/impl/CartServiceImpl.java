package sms.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dao.CartDao;
import sms.dao.PaymentDao;
import sms.dto.ProductDto;
import sms.service.CartService;
import sms.dto.CartDto;
import sms.dto.PaymentDto;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	PaymentDao paymentDao;

	@Override
	public List<CartDto> listCartItems(String user_id) {
		// 특정 사용자의 장바구니 항목을 조회
		return cartDao.listCartItems(user_id);
	}

	@Override
	@Transactional
	public void updateCartItemAndTotal(CartDto cartItemUpdateDto) {
		// 장바구니 내 상품의 수량 업데이트 및 총액 재계산
		cartDao.updateCartItemAndTotal(cartItemUpdateDto);
	}

	@Override
	@Transactional
	public void updateCartState(CartDto cartStatusUpdateDto) {
		// 장바구니 상태 업데이트
		cartDao.updateCartState(cartStatusUpdateDto);
	}

	//	@Override
	//	@Transactional
	//	public void createNewCart(CartDto cartResetDto) {
	//		// 새 장바구니 생성
	//		String newCartId = generateCartId();
	//		cartResetDto.setCart_id(newCartId);
	//		cartDao.createNewCart(cartResetDto);
	//	}

	@Override
	@Transactional	
	public void addProductToCartDetails(CartDto cartItemUpdateDto) {
		// 장바구니에 상품 추가
		cartDao.addProductToCartDetails(cartItemUpdateDto);
	}

	@Override
	public int getStock(String productId) {
		//재고 확인
		return cartDao.getStock(productId);
	}


	//	/**
	//	 * paymentController에 만들걸..... 나중에 시간이 남는다면 옮기자...
	//	 */
	//	@Override
	//	@Transactional
	//	public void completePaymentAndCreateNewCart(PaymentDto paymentDto) {
	//		
	//		//결제정보 저장
	//				paymentDao.savePaymentInfo(paymentDto);
	//		
	//		// 최신 cart_id 가져오기
	//		String latestCartId = cartDao.getLatestCartId();
	//		
	//		// 새 cart_id 생성
	//		String newCartId = generateNewCartId(latestCartId);
	//		
	//		// 동일한 일련번호를 사용하여 payment_id 생성
	//		String newPaymentId = generateNewPaymentId(newCartId);
	//
	//		
	//		// 결제 정보 저장
	//		paymentDto.setPayment_id(newPaymentId);
	//		paymentDto.setCart_id(latestCartId);
	////		paymentDao.savePaymentInfo(paymentDto);
	//		
	//	//	PaymentDto savepayment = new PaymentDto(newPaymentId, latestCartId );
	//		
	//		
	//
	//		// 장바구니 상태 업데이트
	//		CartDto cartStatusUpdateDto = new CartDto();
	//		cartStatusUpdateDto.setCart_id(latestCartId);
	//		cartStatusUpdateDto.setUser_id(paymentDto.getUser_id());
	//		cartStatusUpdateDto.setState("결제완료");
	//		cartDao.updateCartState(cartStatusUpdateDto);
	//
	//		// 새 장바구니 생성
	//		CartDto newCart = new CartDto(newCartId, paymentDto.getUser_id(), "장바구니", new java.sql.Date(System.currentTimeMillis()));
	//		cartDao.insertNewCart(newCart);
	//	}
	//
	//
	//	private String generateNewCartId(String latestCartId) {
	//		// 로직 구현 (latestCartId에서 숫자를 추출하고 1을 더함)
	//		int lastNumber = Integer.parseInt(latestCartId.substring(5)) + 1;
	//		return "CART-" + String.format("%04d", lastNumber);
	//	}
	//
	//	private String generateNewPaymentId(String newCartId) {
	//		// 로직 구현 (오늘 날짜와 newCartId에서 추출한 숫자 사용)
	//		String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
	//		return "SO" + dateString + "-" + newCartId.substring(5);
	//	}

    @Override
    @Transactional
    public void completePaymentAndCreateNewCart(PaymentDto paymentDto) {
        // 기존 cart_id 사용
        String existingCartId = paymentDto.getCart_id();

        // 최신 cart_id 가져오기
        String latestCartId = cartDao.getLatestCartId();

        // 새 cart_id 생성
        String newCartId = generateNewCartId(latestCartId);

        // 동일한 일련번호를 사용하여 payment_id 생성
        String newPaymentId = generateNewPaymentId(latestCartId);

        // 기존 결제 정보 저장
        paymentDao.savePaymentInfo(paymentDto);

        // 기존 장바구니 상태 업데이트
        CartDto cartStatusUpdateDto = new CartDto();
        cartStatusUpdateDto.setCart_id(existingCartId);
        cartStatusUpdateDto.setUser_id(paymentDto.getUser_id());
        cartStatusUpdateDto.setState("결제완료");
        cartDao.updateCartState(cartStatusUpdateDto);

        // 새로운 장바구니 생성
        CartDto newCart = new CartDto(newCartId, paymentDto.getUser_id(), "장바구니", new java.sql.Date(System.currentTimeMillis()));
        cartDao.insertNewCart(newCart);

        // 새로운 결제 정보 생성
        PaymentDto newPayment = new PaymentDto();
        newPayment.setPayment_id(newPaymentId);
        newPayment.setCart_id(newCartId);
        newPayment.setUser_id(paymentDto.getUser_id());
        paymentDao.saveNewPaymentInfo(newPayment);
    }

    private String generateNewCartId(String latestCartId) {
        int lastNumber = Integer.parseInt(latestCartId.substring(5)) + 1;
        return "CART-" + String.format("%04d", lastNumber);
    }

    private String generateNewPaymentId(String latestCartId) {
        String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return "SO" + dateString + "-" + latestCartId.substring(5);
    }

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}


}