package com.xcysoft.foundation.cppt.biz;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.newcapec.framework.core.biz.BaseService;
import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.model.TenantOrg;

/**
 *
 * @author huangxin
 *
 */
public interface TenantOrgService extends BaseService<TenantOrg> {

	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
