package sms.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sms.dao.PaymentDao;
import sms.dto.CartDto;
import sms.dto.PaymentDetailDto;
import sms.dto.PaymentDto;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	  @Override
	    public void savePaymentInfo(PaymentDto paymentDto) {
	        // 결제 정보 저장
		  sqlSessionTemplate.insert("PaymentMapper.savePaymentInfo", paymentDto);
	    }

	    @Override
	    public void updateCartStateToCompleted(CartDto cartDto) {
	        // 결제 상태 변경 (결제중 -> 결제완료)
	    	sqlSessionTemplate.update("PaymentMapper.updateCartStateToCompleted", cartDto);
	    }

	    @Override
	    public void createPaymentRecord(PaymentDto paymentDto) {
	        // 결제 기록 생성
	    	sqlSessionTemplate.insert("PaymentMapper.createPaymentRecord", paymentDto);
	    }

	    @Override
	    public String createPaymentRecordAndNewCart(PaymentDto paymentDto) {
	        sqlSessionTemplate.insert("PaymentMapper.createPaymentRecordAndNewCart", paymentDto);
	        return paymentDto.getNewCartId(); // Assuming newCartId is set in the SQL mapper
	    }
	    
	    @Override
	    public void savePaymentDetail(PaymentDetailDto paymentDetailDto) {
	    	sqlSessionTemplate.insert("PaymentMapper.savePaymentDetail", paymentDetailDto);
	    }

	    
	    @Override
	    public void updatePaymentInfo(PaymentDto paymentDto) {
	    	sqlSessionTemplate.update("PaymentMapper.updatePaymentInfo", paymentDto);
	    }
	    
	    @Override
	    public List<PaymentDetailDto> getPaymentDetails(String cartId) {
	        return sqlSessionTemplate.selectList("PaymentMapper.selectPaymentDetails", cartId);
	    }
//	    @Override
//	    public void saveNewPaymentInfo(PaymentDto paymentDto) {
//	        sqlSessionTemplate.insert("PaymentMapper.saveNewPaymentInfo", paymentDto);
//	    }
	    @Override
	    public boolean existsPaymentId(String paymentId) {
	        Integer count = sqlSessionTemplate.selectOne("PaymentMapper.existsPaymentId", paymentId);
	        return count != null && count > 0;
	    }
		public SqlSessionTemplate getSqlSessionTemplate() {
			return sqlSessionTemplate;
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			this.sqlSessionTemplate = sqlSessionTemplate;
		}


	    
}