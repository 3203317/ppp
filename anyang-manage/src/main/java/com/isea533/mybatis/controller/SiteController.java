//package com.isea533.mybatis.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.isea533.mybatis.model.User;
//
///**
// *
// * @author huangxin (3203317@qq.com)
// *
// */
//@Controller
//public class SiteController {
//
//	private String index_ftl = "site/1.0.1/index";
//
//	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
//	public ModelAndView indexUI() {
//		ModelAndView result = new ModelAndView(index_ftl);
//		// TODO
//		User user = new User();
//		user.setUserName("admin");
//		user.setUserPass("123456");
//		// TODO
//		result.addObject("user", user);
//		return result;
//	}
//}
