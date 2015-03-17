package com.transilink.foundation.cppt.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.transilink.foundation.cppt.dao.base.BaseSoftServiceOrderDao;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
@Repository
@SuppressWarnings("all")
public class SoftServiceOrderDao extends BaseSoftServiceOrderDao {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	public Page findAll(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from ("
				+ "select a.*, b.SOFTSERVICE_NAME, c.TENANT_NAME "
				+ "from s_softserviceorder a, s_softservice b, s_tenant c "
				+ "where a.SOFTSERVICE_ID=b.id and a.TENANT_ID=c.id"
				+ ") t where 1=1");
		if (null != paramMap) {
			if (paramMap.containsKey("softservice_name")
					&& StringUtils.isNotBlank(paramMap.get("softservice_name")
							.toString())) {
				sql.append(" and t.softservice_name like ?");
				param.add("%" + paramMap.get("softservice_name") + "%");
			}
			if (paramMap.containsKey("tenant_name")
					&& StringUtils.isNotBlank(paramMap.get("tenant_name")
							.toString())) {
				sql.append(" and t.tenant_name like ?");
				param.add("%" + paramMap.get("tenant_name") + "%");
			}
		}
		return this.sqlQueryForPage(sql.toString(), param.toArray(), orderby);
	}
}
