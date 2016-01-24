package com.isea533.mybatis.controller;

import net.foreworld.dsession.DistributedSession;
import net.foreworld.dsession.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
@Controller
public class SiteController {

	private String index_ftl = "site/1.0.1/index";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView indexUI(@DistributedSession HttpSession session) {
		ModelAndView result = new ModelAndView(index_ftl);
		// TODO
		Object obj = session.getAttribute("session.user");
		result.addObject("data_session_user", obj);
		return result;
	}
}
