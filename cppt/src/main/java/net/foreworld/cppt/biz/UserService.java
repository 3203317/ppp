package net.foreworld.cppt.biz;

import java.util.Map;

import net.foreworld.cppt.model.User;
import net.foreworld.framework.core.biz.BaseService;
import net.foreworld.framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
public interface UserService extends BaseService<User> {

	Page<User> querys(Map<String, Object> paramMap);

	void delete(String[] ids);
}
