package com.xcysoft.foundation.cppt.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexUI() {
		return getUrl("site.manage.indexUI");
	}
}
