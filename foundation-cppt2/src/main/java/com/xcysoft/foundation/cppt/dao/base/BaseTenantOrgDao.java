package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;
import com.xcysoft.foundation.cppt.model.TenantOrg;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseTenantOrgDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return TenantOrg.class;
	}

	public TenantOrg get(Serializable key) {
		return (TenantOrg) get(getReferenceClass(), key);
	}
}
