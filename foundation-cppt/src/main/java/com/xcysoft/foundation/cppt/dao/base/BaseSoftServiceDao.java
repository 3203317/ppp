package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.SoftService;

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
