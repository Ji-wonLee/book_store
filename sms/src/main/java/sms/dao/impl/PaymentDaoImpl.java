package sms.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.PaymentDao;
import sms.dto.CartDto;
import sms.dto.PaymentDto;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	  @Override
	    public void savePaymentInfo(PaymentDto paymentDto) {
	        // ���� ���� ����
		  sqlSessionTemplate.insert("PaymentMapper.savePaymentInfo", paymentDto);
	    }

	    @Override
	    public void updateCartStateToCompleted(CartDto cartDto) {
	        // ���� ���� ���� (������ -> �����Ϸ�)
	    	sqlSessionTemplate.update("PaymentMapper.updateCartStateToCompleted", cartDto);
	    }

	    @Override
	    public void createPaymentRecord(PaymentDto paymentDto) {
	        // ���� ��� ����
	    	sqlSessionTemplate.insert("PaymentMapper.createPaymentRecord", paymentDto);
	    }

	    @Override
	    public void createPaymentRecordAndNewCart(PaymentDto paymentDto, String newCartId) {
	        // ���� ��� ���� �� ���ο� ��ٱ��� ����
	    	sqlSessionTemplate.insert("PaymentMapper.createPaymentRecord", paymentDto);
	    	sqlSessionTemplate.insert("CartMapper.createNewCart", new CartDto(paymentDto.getUser_id(), newCartId));
	    }

		public SqlSessionTemplate getSqlSessionTemplate() {
			return sqlSessionTemplate;
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			this.sqlSessionTemplate = sqlSessionTemplate;
		} 
}