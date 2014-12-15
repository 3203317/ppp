package com.xcysoft.foundation.cppt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.newcapec.framework.core.handler.MultiViewResource;

import com.xcysoft.foundation.cppt.biz.UserService;
import com.xcysoft.foundation.cppt.model.User;

/**
 *
 * @author huangxin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
@SuppressWarnings("all")
public class UserController extends MultiViewResource {

	@Autowired
	private UserService userService;

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "addUserUI")
	public String addUserUI() {
		User user = userService.get("12");
		System.out.println(user.getUserName());
		return getUrl("user.addUserUI");
	}
}
