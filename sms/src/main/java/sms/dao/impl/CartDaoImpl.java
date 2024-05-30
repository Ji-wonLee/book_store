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
	        // 특정 사용자의 장바구니 항목을 조회
	        return sqlSessionTemplate.selectList("CartMapper.listCartItems", user_id);
	    }

	    @Override
	    public void updateCartItemAndTotal(CartDto cartDto) {
	        // 장바구니 내 상품의 수량 업데이트 및 총액 재계산
	    	sqlSessionTemplate.update("CartMapper.updateCartItemAndTotal", cartDto);
	    }

	    @Override
	    public void updateCartState(CartDto cartDto) {
	        // 장바구니 상태 업데이트
	    	sqlSessionTemplate.update("CartMapper.updateCartState", cartDto);
	    }

	    @Override
	    public void createNewCart(CartDto cartDto) {
	        // 새 장바구니 생성
	    	sqlSessionTemplate.insert("CartMapper.createNewCart", cartDto);
	    }

	    @Override
	    public void addProductToCartDetails(CartDto cartDto) {
	        // 장바구니에 상품 추가
	    	sqlSessionTemplate.insert("CartMapper.addProductToCartDetails", cartDto);
	    }

		public SqlSessionTemplate getSqlSessionTemplate() {
			return sqlSessionTemplate;
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			this.sqlSessionTemplate = sqlSessionTemplate;
		}
	

}
