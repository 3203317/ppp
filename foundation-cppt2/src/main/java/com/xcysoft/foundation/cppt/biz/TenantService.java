package com.xcysoft.foundation.cppt.biz;

import java.util.LinkedHashMap;
import java.util.Map;

import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.model.Tenant;

/**
 *
 * @author huangxin
 *
 */
public interface TenantService extends BaseService<Tenant> {

	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
