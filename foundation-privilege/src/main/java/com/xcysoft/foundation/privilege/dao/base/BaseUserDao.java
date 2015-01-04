package com.xcysoft.foundation.privilege.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.privilege.model.User;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseUserDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return User.class;
	}

	public User get(Serializable key) {
		return (User) get(getReferenceClass(), key);
	}
}
