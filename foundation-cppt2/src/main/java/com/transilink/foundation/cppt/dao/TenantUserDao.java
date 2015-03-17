package com.transilink.foundation.cppt.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.transilink.foundation.cppt.dao.base.BaseTenantUserDao;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class TenantUserDao extends BaseTenantUserDao {

	/**
	 *
	 * @param paramMap
	 * @param orderby
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select t.* from "
						+ "(SELECT a.*, b.TENANT_NAME, b.CORP_NAME FROM s_tenantuser a, s_tenant b where a.TENANT_ID=b.id) t "
						+ "where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("tenant_name")
					&& StringUtils.isNotBlank(paramMap.get("tenant_name")
							.toString())) {
				sql.append(" and t.tenant_name like ?");
				param.add("%" + paramMap.get("tenant_name") + "%");
			}
			if (paramMap.containsKey("user_name")
					&& StringUtils.isNotBlank(paramMap.get("user_name")
							.toString())) {
				sql.append(" and t.user_name like ?");
				param.add("%" + paramMap.get("user_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), orderby);
	}
}
