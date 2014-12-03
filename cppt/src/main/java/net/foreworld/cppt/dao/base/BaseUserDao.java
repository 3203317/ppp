package net.foreworld.cppt.dao.base;

import net.foreworld.cppt.model.User;
import net.foreworld.framework.core.dao.hibernate.HibernateEntityDao;

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
