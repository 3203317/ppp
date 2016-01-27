package com.isea533.mybatis.controller;

import javax.servlet.http.HttpSession;

import net.foreworld.dsession.DistributedSessionContext;

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
	public ModelAndView indexUI(HttpSession session) {
		ModelAndView result = new ModelAndView(index_ftl);
		// TODO
		Object obj = session.getAttribute("session.user");
		result.addObject("data_session_user", obj);
		// 在线人数
		result.addObject("data_session_online",
				DistributedSessionContext.getOnlineNum());
		return result;
	}
}
