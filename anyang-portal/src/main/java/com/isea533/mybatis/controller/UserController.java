package com.isea533.mybatis.controller;

import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = { "/user/logout" }, method = RequestMethod.GET)
	public String logoutUI(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}

	@RequestMapping(value = { "/user/login" }, method = RequestMethod.POST, produces = "application/json")
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView result = new ModelAndView();
		// TODO
		if ("admin".equals(user.getUserName())
				&& "123456".equals(user.getUserPass())) {
			result.addObject("success", true);
			// TODO
			session.setAttribute("session.user", user);
		} else {
			result.addObject("success", false);
			result.addObject("msg", "用户名或密码输入错误");
		} // END
		return result;
	}
}
