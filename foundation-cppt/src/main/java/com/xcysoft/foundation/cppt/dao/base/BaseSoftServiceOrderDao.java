package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.SoftServiceOrder;

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
