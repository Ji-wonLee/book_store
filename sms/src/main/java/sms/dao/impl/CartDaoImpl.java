package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sms.dto.ProductDto;
import sms.dto.CartDto;
import sms.dto.PaymentDto;
import sms.dao.CartDao;

public class CartDaoImpl implements CartDao {


	@Autowired
	SqlSessionTemplate sqlSessionTemplate;


	@Override
    public List<ProductDto> listCartItems(String userId) {
        // 장바구니에 있는 상품들을 조회합니다. 
        return sqlSessionTemplate.selectList("CartMapper.listCartItems", userId);
    }

	@Override
    public void addProductToCart(String userId, String productId, int quantity) {
        // 현재 활성화된 장바구니를 찾거나 새로 만들고 해당 장바구니에 상품을 추가
        String cartId = sqlSessionTemplate.selectOne("CartMapper.findActiveCartId", userId);
        if (cartId == null) {
            cartId = this.createNewCart(userId);
        }
        this.addProductToCartDetails(cartId.toString(), productId, quantity);
    }

    @Override
    public void addProductToCartDetails(String cartId, String productId, int quantity) {
        // 장바구니에 상품의 상세 정보를 추가하거나 업데이트
    	sqlSessionTemplate.update("CartMapper.addProductToCartDetails", new HashMap<String, Object>() {{
            put("cartId", cartId);
            put("productId", productId);
            put("quantity", quantity);
        }});
    }

    @Override
    public void updateCartItemAndTotal(String cartId, String productId, int quantity) {
        // 특정 장바구니의 상품 수량을 업데이트하고 총 가격을 재계산
    	sqlSessionTemplate.update("CartMapper.updateCartItemAndTotal", new HashMap<String, Object>() {{
            put("cartId", cartId);
            put("productId", productId);
            put("quantity", quantity);
        }});
    }

    @Override
    public void savePaymentInfo(PaymentDto paymentDto) {
        // 결제 정보를 데이터베이스에 저장
    	sqlSessionTemplate.insert("CartMapper.savePaymentInfo", paymentDto);
    }

    @Override
    public void updateCartState(String userId, String state) {
        // 특정 사용자의 장바구니 상태를 업데이트
    	sqlSessionTemplate.update("CartMapper.updateCartState", new HashMap<String, Object>() {{
            put("userId", userId);
            put("state", state);
        }});
    }

    @Override
    public void updateCartStateToCompleted(String userId) {
        // 결제가 완료된 후, 장바구니 상태를 '결제완료'로 변경
    	sqlSessionTemplate.update("CartMapper.updateCartStateToCompleted", userId);
    }

    @Override
    public String createNewCart(String userId) {
        // 새로운 장바구니를 생성하고 생성된 장바구니의 ID를 반환
        return sqlSessionTemplate.selectOne("CartMapper.createNewCart", userId);
    }

    @Override
    public String createPaymentRecord(Integer cartId) {
        // 새로운 결제 기록을 생성하고 생성된 결제 ID를 반환
        return sqlSessionTemplate.selectOne("CartMapper.createPaymentRecord", cartId);
    }
	
	

}
