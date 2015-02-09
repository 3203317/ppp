package com.xcysoft.foundation.oa.controller;

import java.util.LinkedHashMap;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.handler.MultiViewResource;
import cn.newcapec.framework.core.utils.fileUtils.SysConfigUtil;

/**
 *
 * @author huangxin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/site")
@SuppressWarnings("all")
public class SiteController extends MultiViewResource {

	private LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();

	/**
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));

		JSONObject jsonObj = getJsonObject();
		if (null != jsonObj && jsonObj.containsKey("tenant")) {
			modelMap.put("tenant", jsonObj.get("tenant"));
		}

		if (null != jsonObj) {
			modelMap.put("urlparams", jsonObj.toString());
		}

		return toView(getUrl("site.indexUI"), modelMap);
	}

}
