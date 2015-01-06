package com.xcysoft.foundation.cppt.biz;

import java.util.Map;

import cn.newcapec.framework.core.biz.BaseService;
import cn.newcapec.framework.core.utils.pagesUtils.Page;

import com.xcysoft.foundation.cppt.model.Role;

/**
 *
 * @author huangxin
 *
 */
public interface RoleService extends BaseService<Role> {

	/**
	 *
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page findList(Map<String, Object> paramMap);
}
