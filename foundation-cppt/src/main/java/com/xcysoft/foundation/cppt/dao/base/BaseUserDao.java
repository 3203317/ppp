package com.xcysoft.foundation.cppt.dao.base;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.User;

@SuppressWarnings("all")
public abstract class BaseUserDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return User.class;
	}

	public User get(java.io.Serializable key) {
		return (User) get(getReferenceClass(), key);
	}
}
