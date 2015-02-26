package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.TenantUser;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseTenantUserDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return TenantUser.class;
	}

	public TenantUser get(Serializable key) {
		return (TenantUser) get(getReferenceClass(), key);
	}
}
