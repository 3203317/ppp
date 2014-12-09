package com.xcysoft.cppt.biz;

import java.util.Map;

import com.xcysoft.cppt.model.User;
import com.xcysoft.framework.core.biz.BaseService;
import com.xcysoft.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
public interface UserService extends BaseService<User> {

	Page<User> querys(Map<String, Object> paramMap);

	void delete(String[] ids);
}
