package com.cn.ub.dao.user;

import java.util.List;

import com.cn.ub.dao.BaseDAO;
import com.cn.ub.entry.user.User;
import com.cn.ub.entry.user.UserVO;

public interface UserDAO extends BaseDAO<User> {
	
	User getUser(String username)throws Exception;
	
	UserVO getUserVO(String id)throws Exception;
	
	List<User> getUserList(String userType)throws Exception;

}
