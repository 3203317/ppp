package com.xcysoft.cppt.dao;

import junit.framework.TestCase;

/**
 *
 * @author huangxin
 *
 */
public class TestUserDao extends TestCase {

	private UserDao userDao;

	public void testFindAll() {
		userDao = new UserDao();
		userDao.findAll(null);
	}
}
