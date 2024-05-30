package sms.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sms.dto.ProductDto;
import sms.dto.CartDto;
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
	        // ��ٱ��� ���� ������Ʈ
	    	sqlSessionTemplate.update("CartMapper.updateCartState", cartDto);
	    }

	    @Override
	    public void createNewCart(CartDto cartDto) {
	        // �� ��ٱ��� ����
	    	sqlSessionTemplate.insert("CartMapper.createNewCart", cartDto);
	    }

	    @Override
	    public void addProductToCartDetails(CartDto cartDto) {
	        // ��ٱ��Ͽ� ��ǰ �߰�
	    	sqlSessionTemplate.insert("CartMapper.addProductToCartDetails", cartDto);
	    }

		public SqlSessionTemplate getSqlSessionTemplate() {
			return sqlSessionTemplate;
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			this.sqlSessionTemplate = sqlSessionTemplate;
		}
	

}
