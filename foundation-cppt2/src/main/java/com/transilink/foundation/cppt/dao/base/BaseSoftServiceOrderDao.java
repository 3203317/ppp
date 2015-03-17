package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.SoftServiceOrder;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseSoftServiceOrderDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return SoftServiceOrder.class;
	}

	public SoftServiceOrder get(Serializable key) {
		return (SoftServiceOrder) get(getReferenceClass(), key);
	}
}
