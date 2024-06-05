package sms.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sms.dto.UsersDto;
import sms.dao.UsersDao;

@Service
public class UsersDaoImpl implements UsersDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	//����� ����
	@Transactional
	public List<UsersDto> selectUsersList() throws Exception {
		return sqlSessionTemplate.selectList("users.selectUsersList");
	}
	//����� ȸ������
	@Transactional
	public int insertUsers(HashMap<String, Object> map) {
		return sqlSessionTemplate.insert("users.insertUsers", map);
	}
	//����� �α���
	@Transactional
	public String selectLoginCheck(HashMap<String, Object> map) {
		return sqlSessionTemplate.selectOne("users.selectLoginCheck", map);
	}
	//����� ����
	@Transactional
	public int deleteUsers(HashMap<String, Object> map) {
		return sqlSessionTemplate.delete("users.deleteUsers",map);
	}
	//����� ���̵� �ߺ� üũ
	@Transactional
	public int selectIdCheck(String id) throws Exception {
		return sqlSessionTemplate.selectOne("users.selectIdCheck" , id);
	}
	//����� ��� ���� gname ��ȯ
	@Transactional
	public String selectCheckClient(HashMap<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("users.selectCheckClient", map);
	}
	//����� ������
	@Transactional
	public List<UsersDto> selectMyInfo(String user_id) throws Exception {
		return sqlSessionTemplate.selectList("users.selectMyInfo", user_id);
	}
	//����� ���� ����
	@Transactional
	public int updateMyInfo(HashMap<String, Object> map) throws Exception {
		return sqlSessionTemplate.update("users.updateMyInfo", map);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
