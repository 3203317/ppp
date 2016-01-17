package com.isea533.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.isea533.mybatis.model.User;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
@Controller
public class UserController {

	private String login_ftl = "user/1.0.1/login";

	@RequestMapping(value = { "/user/login" }, method = RequestMethod.GET)
	public ModelAndView loginUI() {
		ModelAndView result = new ModelAndView(login_ftl);
		// TODO
		User user = new User();
		user.setUserName("admin");
		user.setUserPass("123456");
		// TODO
		result.addObject("user", user);
		return result;
	}

	@RequestMapping(value = { "/user/login" }, method = RequestMethod.POST, produces = "application/json")
	public ModelAndView login(User pj) {
		ModelAndView result = new ModelAndView();
		// TODO
		if ("admin".equals(pj.getUserName())
				&& "123456".equals(pj.getUserPass())) {
			result.addObject("success", "true");
		} else {
			result.addObject("success", "false");
		} // END
		return result;
	}
}
