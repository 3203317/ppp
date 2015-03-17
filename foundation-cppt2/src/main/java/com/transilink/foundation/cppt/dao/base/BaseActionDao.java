package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.Action;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseActionDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Action.class;
	}

	public Action get(Serializable key) {
		return (Action) get(getReferenceClass(), key);
	}
}
