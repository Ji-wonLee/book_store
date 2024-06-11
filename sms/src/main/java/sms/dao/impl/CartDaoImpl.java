package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sms.dto.ProductDto;
import sms.dto.CartDto;
import sms.dto.Inventory;
import sms.dto.PaymentDto;
import sms.dao.CartDao;


@Repository
public class CartDaoImpl implements CartDao {


	@Autowired
	SqlSessionTemplate sqlSessionTemplate;


	 @Override
	    public List<CartDto> listCartItems(String user_id) {
	        // Ư�� ������� ��ٱ��� �׸��� ��ȸ	
	        return sqlSessionTemplate.selectList("CartMapper.listCartItems", user_id);
	    }

	    @Override
	    public void updateCartItemAndTotal(CartDto cartDto) {
	        // ��ٱ��� �� ��ǰ�� ���� ������Ʈ �� �Ѿ� ����
	    	sqlSessionTemplate.update("CartMapper.updateCartItemAndTotal", cartDto);
	    }

	    @Override
	    public void updateCartState(CartDto cartDto) {
	    	 sqlSessionTemplate.update("CartMapper.updateCartState", cartDto);
	    }

	    @Override
	    public void updateStock(Inventory inventory) {
	    	sqlSessionTemplate.update("CartMapper.updateStock", inventory);
	    }

	    @Override
	    public String createNewCart(CartDto cartDto) {
	        sqlSessionTemplate.insert("CartMapper.insertNewCart", cartDto);
	        return cartDto.getCart_id(); // cart_id�� cartDto�� �̸� ������
	    }
	    
	    @Override
	    public int getStock(String product_id) {
	    	//���Ȯ��
	    	return sqlSessionTemplate.selectOne("CartMapper.getStock", product_id);
	    }
	    
	    //ū cartid ã��
	    @Override
	    public String getMaxCartId() {
	        return sqlSessionTemplate.selectOne("CartMapper.getMaxCartId");
	    }
	    // �ֽ� id 
	    @Override
	    public String getLatestCartId() {
	        return sqlSessionTemplate.selectOne("CartMapper.getLatestCartId");
	    }
	    
	    // ���ο� Cart ����
	    @Override
	    public void insertNewCart(CartDto cartDto) {
	        sqlSessionTemplate.insert("CartMapper.insertNewCart", cartDto);
	    }
	    
	    @Override
	    public String findCartId(String user_id) {
	        return sqlSessionTemplate.selectOne("CartMapper.selectCartid", user_id);
	    }

	    @Override
	    public int addCart(CartDto cartDto) {
	        return sqlSessionTemplate.update("CartMapper.addCartProduct", cartDto);
	    }
	    private String generateNewCartId() {
	        String lastCartId = sqlSessionTemplate.selectOne("CartMapper.getLatestCartId");
	        if (lastCartId == null || lastCartId.isEmpty()) {
	            return "CART-0001"; // Default starting value if no carts exist
	        } else {
	            int lastNumber = Integer.parseInt(lastCartId.substring(5)) + 1;
	            return "CART-" + String.format("%04d", lastNumber);
	        }
	    }
		public SqlSessionTemplate getSqlSessionTemplate() {
			return sqlSessionTemplate;
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			this.sqlSessionTemplate = sqlSessionTemplate;
		}
	

}