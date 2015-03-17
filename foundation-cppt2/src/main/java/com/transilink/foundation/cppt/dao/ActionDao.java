package com.transilink.foundation.cppt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.transilink.foundation.cppt.dao.base.BaseActionDao;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class ActionDao extends BaseActionDao {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select * from s_action t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("action_name")
					&& StringUtils.isNotBlank(paramMap.get("action_name")
							.toString())) {
				sql.append(" and t.action_name like ?");
				param.add("%" + paramMap.get("action_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), null);
	}
}
