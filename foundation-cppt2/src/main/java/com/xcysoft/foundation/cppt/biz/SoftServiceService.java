package com.xcysoft.foundation.cppt.biz;

import java.util.Map;

import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.model.SoftService;

/**
 *
 * @author huangxin
 *
 */
public interface SoftServiceService extends BaseService<SoftService> {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap);
}
