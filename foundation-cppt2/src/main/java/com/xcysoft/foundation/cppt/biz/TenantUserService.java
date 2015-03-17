package com.xcysoft.foundation.cppt.biz;

import java.util.LinkedHashMap;
import java.util.Map;

import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.model.TenantUser;

/**
 *
 * @author huangxin
 *
 */
public interface TenantUserService extends BaseService<TenantUser> {

	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
