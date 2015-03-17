package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.SoftService;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseSoftServiceDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return SoftService.class;
	}

	public SoftService get(Serializable key) {
		return (SoftService) get(getReferenceClass(), key);
	}
}
