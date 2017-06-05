package com.cn.ub.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.ub.dao.user.UserDAO;
import com.cn.ub.entry.user.User;
import com.cn.ub.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public void save(User obj) throws Exception {
		userDAO.save(obj);
	}

	public void update(User obj) throws Exception {
		userDAO.update(obj);

	}

	public void deleteById(String id) throws Exception {
		userDAO.deleteById(id);

	}

	public List<User> getList(User obj) throws Exception {

		return userDAO.getList(obj);
	}

	public User getObjById(String id) throws Exception {

		return userDAO.getObjById(id);
	}

	@Override
	public User getUser(String username) throws Exception {
		
		return userDAO.getUser(username);
	}

}
