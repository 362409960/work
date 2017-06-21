package com.cn.ub.service.impl.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.ub.common.MD5;
import com.cn.ub.common.UuidUtils;
import com.cn.ub.dao.user.UserCorrespondDAO;
import com.cn.ub.dao.user.UserDAO;
import com.cn.ub.entry.user.User;
import com.cn.ub.entry.user.UserCorrespond;
import com.cn.ub.entry.user.UserVO;
import com.cn.ub.service.user.UserUtilsService;
@Service
public class UserUtilsServiceImpl implements UserUtilsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserCorrespondDAO userCorrespondDAO;

	@Override
	public void saveUserVO(UserVO uservo) throws Exception {
		User user = new User();
		user.setId(UuidUtils.getUuid());
		user.setState("0");
		user.setPassword(MD5.Md5("123456"));
		user.setCreateTime(new Date());
		user.setUsername(uservo.getUsername());
		user.setUserType(uservo.getUserType());
		userDAO.save(user);
		if (StringUtils.isNotEmpty(uservo.getUserUId())){
			UserCorrespond uc = new UserCorrespond();
			uc.setId(UuidUtils.getUuid());
			uc.setState("0");
			uc.setUserUId(uservo.getUserUId());
			uc.setUserVId(uservo.getUserVId());
			userCorrespondDAO.save(uc);
		}
		
		
	}


	@Override
	public void updateUserVO(UserVO uservo) throws Exception {
		User user = new User();
		user.setId(uservo.getId());
		user.setUsername(uservo.getUsername());
		user.setUpdateTime(new Date());
		userDAO.update(user);
		if (StringUtils.isNotEmpty(uservo.getUserVId()) && StringUtils.isNotEmpty(uservo.getUserUId())){
			//先删除，后新增
			userCorrespondDAO.deleteById(uservo.getUserVId());
			
			UserCorrespond uc = new UserCorrespond();
			uc.setId(UuidUtils.getUuid());
			uc.setState("0");
			uc.setUserUId(uservo.getUserUId());
			uc.setUserVId(uservo.getUserVId());
			userCorrespondDAO.save(uc);
		}

	}


	@Override
	public UserVO getUserVO(String id) throws Exception {
	
		return userDAO.getUserVO(id);
	}

}
