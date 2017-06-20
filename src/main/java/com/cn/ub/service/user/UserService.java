package com.cn.ub.service.user;

import java.util.List;

import com.cn.ub.entry.user.User;
import com.cn.ub.service.BaseService;

public interface UserService extends BaseService<User>{
	
	User getUser(String username)throws Exception;
	
	List<User> getUserList(String userType)throws Exception;

}
