package com.xcysoft.cppt.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xcysoft.cppt.dao.base.BaseUserDao;
import com.xcysoft.cppt.model.User;

import framework.core.utils.pagesUtils.Page;

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
	public User getByName(String name) {
		return null;
	}

	public Page findAll(Map<String, Object> paramMap) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM S_USER t WHERE 1=1");
		if (null != paramMap) {
			// TODO
		}
		return this.sqlqueryForpage(sql.toString(), param, null);
	}

}
