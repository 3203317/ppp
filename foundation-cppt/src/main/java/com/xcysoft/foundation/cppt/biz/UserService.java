package com.xcysoft.foundation.cppt.biz;

import cn.newcapec.framework.core.biz.BaseService;
import cn.newcapec.framework.core.rest.Msg;

import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
public interface UserService extends BaseService<User> {

	/**
	 * 用户登录验证
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public Msg loginValidate(String userName, String password);
}
