package com.xcysoft.foundation.cppt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.dao.base.BaseSoftServiceDao;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class SoftServiceDao extends BaseSoftServiceDao {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select * from s_softservice t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("softservice_name")
					&& StringUtils.isNotBlank(paramMap.get("softservice_name")
							.toString())) {
				sql.append(" and t.softservice_name like ?");
				param.add("%" + paramMap.get("softservice_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), null);
	}
}
