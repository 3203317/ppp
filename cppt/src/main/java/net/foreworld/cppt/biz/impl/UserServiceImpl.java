package net.foreworld.cppt.biz.impl;

import java.util.Map;

import net.foreworld.cppt.biz.UserService;
import net.foreworld.cppt.dao.UserDao;
import net.foreworld.cppt.model.User;
import net.foreworld.framework.core.utils.pagesUtils.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author huangxin
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void removeUnused(String id) {
		userDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public User get(String id) {
		return null;
	}

	@Override
	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Page<User> querys(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public void delete(String[] ids) {
		userDao.delete(ids);
	}
}
