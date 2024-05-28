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
        // ��ٱ��Ͽ� �ִ� ��ǰ���� ��ȸ�մϴ�. 
        return sqlSessionTemplate.selectList("CartMapper.listCartItems", userId);
    }

	@Override
    public void addProductToCart(String userId, String productId, int quantity) {
        // ���� Ȱ��ȭ�� ��ٱ��ϸ� ã�ų� ���� ����� �ش� ��ٱ��Ͽ� ��ǰ�� �߰�
        String cartId = sqlSessionTemplate.selectOne("CartMapper.findActiveCartId", userId);
        if (cartId == null) {
            cartId = this.createNewCart(userId);
        }
        this.addProductToCartDetails(cartId.toString(), productId, quantity);
    }

    @Override
    public void addProductToCartDetails(String cartId, String productId, int quantity) {
        // ��ٱ��Ͽ� ��ǰ�� �� ������ �߰��ϰų� ������Ʈ
    	sqlSessionTemplate.update("CartMapper.addProductToCartDetails", new HashMap<String, Object>() {{
            put("cartId", cartId);
            put("productId", productId);
            put("quantity", quantity);
        }});
    }

    @Override
    public void updateCartItemAndTotal(String cartId, String productId, int quantity) {
        // Ư�� ��ٱ����� ��ǰ ������ ������Ʈ�ϰ� �� ������ ����
    	sqlSessionTemplate.update("CartMapper.updateCartItemAndTotal", new HashMap<String, Object>() {{
            put("cartId", cartId);
            put("productId", productId);
            put("quantity", quantity);
        }});
    }

    @Override
    public void savePaymentInfo(PaymentDto paymentDto) {
        // ���� ������ �����ͺ��̽��� ����
    	sqlSessionTemplate.insert("CartMapper.savePaymentInfo", paymentDto);
    }

    @Override
    public void updateCartState(String userId, String state) {
        // Ư�� ������� ��ٱ��� ���¸� ������Ʈ
    	sqlSessionTemplate.update("CartMapper.updateCartState", new HashMap<String, Object>() {{
            put("userId", userId);
            put("state", state);
        }});
    }

    @Override
    public void updateCartStateToCompleted(String userId) {
        // ������ �Ϸ�� ��, ��ٱ��� ���¸� '�����Ϸ�'�� ����
    	sqlSessionTemplate.update("CartMapper.updateCartStateToCompleted", userId);
    }

    @Override
    public String createNewCart(String userId) {
        // ���ο� ��ٱ��ϸ� �����ϰ� ������ ��ٱ����� ID�� ��ȯ
        return sqlSessionTemplate.selectOne("CartMapper.createNewCart", userId);
    }

    @Override
    public String createPaymentRecord(Integer cartId) {
        // ���ο� ���� ����� �����ϰ� ������ ���� ID�� ��ȯ
        return sqlSessionTemplate.selectOne("CartMapper.createPaymentRecord", cartId);
    }
	
	

}
