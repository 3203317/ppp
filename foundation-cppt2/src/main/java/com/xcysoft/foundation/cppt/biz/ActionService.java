package com.xcysoft.foundation.cppt.biz;

import java.util.Map;

import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.model.Action;

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
