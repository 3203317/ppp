package com.xcysoft.foundation.cppt.biz;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.newcapec.framework.core.biz.BaseService;
import cn.newcapec.framework.core.utils.pagesUtils.Page;

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
	public Page findChildren(Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby);
}
