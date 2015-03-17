package com.transilink.foundation.cppt.biz;

import java.util.Map;

import com.transilink.foundation.cppt.model.Action;
import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
public interface ActionService extends BaseService<Action> {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap);
}
