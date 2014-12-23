package com.xcysoft.foundation.cppt.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.handler.MultiViewResource;

/**
 *
 * @author huangxin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/manage")
@SuppressWarnings("all")
public class SiteController extends MultiViewResource {

	/**
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexUI(ModelMap modelMap) {
		modelMap.put("cdn", "http://localhost:1234/js/");
		modelMap.put("virtualPath", "/controller/");
		return toView(getUrl("manage.indexUI"), modelMap);
	}
}
