package com.xcysoft.foundation.privilege.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.privilege.dao.base.BaseUserDao;
import com.xcysoft.foundation.privilege.model.User;

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
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select * from s_user t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("user_name")
					&& StringUtils.isNotBlank(paramMap.get("user_name")
							.toString())) {
				sql.append(" and t.user_name like ?");
				param.add("%" + paramMap.get("user_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), null);
	}
}
