package net.foreworld.cppt.biz;

import java.util.Map;

import net.foreworld.cppt.model.User;
import framework.core.biz.BaseService;
import framework.core.utils.pagesUtils.Page;

/**
 *
 * @author huangxin
 *
 */
public interface UserService extends BaseService<User> {

	Page<User> querys(Map<String, Object> paramMap);

	void delete(String[] ids);
}
