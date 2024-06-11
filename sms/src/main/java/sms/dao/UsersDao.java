package sms.dao;

import java.util.HashMap;
import java.util.List;

import sms.dto.UsersDto;

public interface UsersDao {
	List<UsersDto> selectUsersList() throws Exception;
	
	int selectIdCheck(String id) throws Exception;

	int insertUsers(HashMap<String, Object> param);

	int deleteUsers(HashMap<String, Object> map);
	
	String selectLoginCheck(HashMap<String, Object> param) throws Exception;
	
	String selectCheckClient(HashMap<String, Object> param) throws Exception;
	
	List<UsersDto> selectMyInfo(String id) throws Exception;
	
	int updateMyInfo(HashMap<String, Object> param) throws Exception;
}