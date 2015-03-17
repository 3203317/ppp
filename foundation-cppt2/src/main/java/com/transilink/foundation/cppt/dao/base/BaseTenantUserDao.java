package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.TenantUser;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

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
