package com.xcysoft.foundation.cppt.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xcysoft.foundation.cppt.dao.base.BaseUserDao;
import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class UserDao extends BaseUserDao {

	/**
	 *
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from "
				+ getReferenceClass().getName() + " where USER_NAME=?");
		return (User) this.findForObject(hql.toString(), new Object[] { name });
	}
}
