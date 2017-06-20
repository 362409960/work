package com.cn.ub.service.user;

import com.cn.ub.entry.user.UserVO;

public interface UserUtilsService {
	
	void saveUserVO(UserVO uservo)throws Exception;
	
	void updateUserVO(UserVO uservo) throws Exception;
	
	UserVO getUserVO(String id)throws Exception;


}
