package com.xcysoft.foundation.cppt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newcapec.framework.core.exception.asserts.AssertObject;
import cn.newcapec.framework.core.handler.MultiViewResource;
import cn.newcapec.framework.core.rest.Msg;

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
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addUserUI() {
		User user = userService.get("12");
		System.out.println(user.getUserName());
		return getUrl("user.addUserUI");
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Msg addUser(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				msg.setSuccess(false);
				msg.setMsg("保存成功");
			}
		});
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginUI() {
		return getUrl("user.loginUI");
	}
}
