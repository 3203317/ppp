package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.Role;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseRoleDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Role.class;
	}

	public Role get(Serializable key) {
		return (Role) get(getReferenceClass(), key);
	}
}
