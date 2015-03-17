package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.Tenant;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseTenantDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Tenant.class;
	}

	public Tenant get(Serializable key) {
		return (Tenant) get(getReferenceClass(), key);
	}
}
