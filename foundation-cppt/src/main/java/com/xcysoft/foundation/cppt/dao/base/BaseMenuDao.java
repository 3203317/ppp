package com.xcysoft.foundation.cppt.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.cppt.model.Menu;

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
