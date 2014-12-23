package com.xcysoft.foundation.cppt.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.dao.base.BaseUserDao;
import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class UserDao extends BaseUserDao {

	/**
	 *
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM "
				+ getReferenceClass().getName() + " WHERE USER_NAME=?");
		return (User) this.findForObject(hql.toString(), new Object[] { name });
	}

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select * from s_user where 1=1");
		return this.sqlQueryForPage(sql.toString(), param, null);
	}
}
