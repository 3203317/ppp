package com.xcysoft.foundation.cppt.biz;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.transilink.framework.core.biz.BaseService;
import com.transilink.framework.core.utils.pagesUtils.Page;
import com.xcysoft.foundation.cppt.model.Menu;

/**
 *
 * @author huangxin
 *
 */
public interface MenuService extends BaseService<Menu> {

	/**
	 *
	 * @param paramMap
	 * @param orderby
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);

	@SuppressWarnings("rawtypes")
	public List findChildren(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
