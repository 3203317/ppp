package com.xcysoft.foundation.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xcysoft.framework.core.handler.MultiViewResource;

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

	@RequestMapping(value = "addUserUI")
	public String addUserUI() {
		return getUrl("user.addUserUI");
	}
}
