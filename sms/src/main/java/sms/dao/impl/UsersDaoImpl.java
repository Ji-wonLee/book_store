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
	//사용자 관리
	@Transactional
	public List<UsersDto> selectUsersList() throws Exception {
		return sqlSessionTemplate.selectList("users.selectUsersList");
	}
	//사용자 회원가입
	@Transactional
	public int insertUsers(HashMap<String, Object> map) {
		return sqlSessionTemplate.insert("users.insertUsers", map);
	}
	//사용자 로그인
	@Transactional
	public String selectLoginCheck(HashMap<String, Object> map) {
		return sqlSessionTemplate.selectOne("users.selectLoginCheck", map);
	}
	//사용자 삭제
	@Transactional
	public int deleteUsers(HashMap<String, Object> map) {
		return sqlSessionTemplate.delete("users.deleteUsers",map);
	}
	//사용자 아이디 중복 체크
	@Transactional
	public int selectIdCheck(String id) throws Exception {
		return sqlSessionTemplate.selectOne("users.selectIdCheck" , id);
	}
	//사용자 등급 관리 gname 반환
	@Transactional
	public String selectCheckClient(HashMap<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("users.selectCheckClient", map);
	}
	//사용자 내정보
	@Transactional
	public List<UsersDto> selectMyInfo(String user_id) throws Exception {
		return sqlSessionTemplate.selectList("users.selectMyInfo", user_id);
	}
	//사용자 정보 수정
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
