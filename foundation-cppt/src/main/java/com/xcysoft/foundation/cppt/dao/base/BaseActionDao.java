package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.Action;
import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseActionDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return User.class;
	}

	public Action get(Serializable key) {
		return (Action) get(getReferenceClass(), key);
	}
}
