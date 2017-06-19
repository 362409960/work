package com.cn.ub.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.ub.dao.user.UserCorrespondDAO;
import com.cn.ub.entry.user.UserCorrespond;
import com.cn.ub.service.user.UserCorrespondService;
@Service
public class UserCorrespondServiceImpl implements UserCorrespondService {
	
	@Autowired
	private UserCorrespondDAO userCorrespondDAO;

	@Override
	public void save(UserCorrespond obj) throws Exception {
		userCorrespondDAO.save(obj);

	}

	@Override
	public void update(UserCorrespond obj) throws Exception {
		userCorrespondDAO.update(obj);

	}

	@Override
	public void deleteById(String id) throws Exception {
		userCorrespondDAO.deleteById(id);

	}

	@Override
	public List<UserCorrespond> getList(UserCorrespond obj) throws Exception {
		
		return userCorrespondDAO.getList(obj);
	}

	@Override
	public UserCorrespond getObjById(String id) throws Exception {
		
		return userCorrespondDAO.getObjById(id);
	}

}
