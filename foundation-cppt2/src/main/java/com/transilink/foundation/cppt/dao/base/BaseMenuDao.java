package com.transilink.foundation.cppt.dao.base;

import java.io.Serializable;

import com.transilink.foundation.cppt.model.Menu;
import com.transilink.framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseMenuDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Menu.class;
	}

	public Menu get(Serializable key) {
		return (Menu) get(getReferenceClass(), key);
	}
}
