package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.Tenant;

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
