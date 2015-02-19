package com.xcysoft.foundation.cppt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.dao.base.BaseTenantDao;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class TenantDao extends BaseTenantDao {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(
				"select * from s_tenant t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("tenant_name")
					&& StringUtils.isNotBlank(paramMap.get("tenant_name")
							.toString())) {
				sql.append(" and t.tenant_name like ?");
				param.add("%" + paramMap.get("tenant_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), null);
	}
}
