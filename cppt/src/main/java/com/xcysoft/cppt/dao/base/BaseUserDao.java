package com.xcysoft.cppt.dao.base;

import com.xcysoft.cppt.model.User;
import framework.core.dao.hibernate.HibernateEntityDao;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseUserDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return User.class;
	}
}
