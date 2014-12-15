package com.xcysoft.foundation.cppt.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xcysoft.foundation.cppt.biz.UserService;
import com.xcysoft.foundation.cppt.dao.UserDao;
import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
@Service("userService")
@Transactional
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public void removeUnused(String id) {
		userDao.delete(id);
	}

	@Override
	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}
}
