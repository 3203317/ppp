package com.xcysoft.foundation.cppt.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.exception.asserts.AssertObject;
import cn.newcapec.framework.core.handler.MultiViewResource;
import cn.newcapec.framework.core.rest.Msg;
import cn.newcapec.framework.core.utils.fileUtils.SysConfigUtil;
import cn.newcapec.framework.core.utils.pagesUtils.Page;
import cn.newcapec.framework.core.utils.pagesUtils.PageContext;
import cn.newcapec.framework.core.utils.pagesUtils.PageView;

import com.xcysoft.foundation.cppt.biz.MenuService;

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

	@Autowired
	private MenuService menuService;

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

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pid", "00");
		PageContext.setPageSize(Integer.MAX_VALUE);

		orderby.put("sort", "asc");
		Page page = menuService.findList(paramMap, orderby);
		PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
				PageContext.getPageSize(), PageContext.getOffset());
		pageView.setQueryResult(page);
		modelMap.put("menuView", pageView);

		page = getChildMenus();
		pageView = new PageView<Map<String, Object>>(PageContext.getPageSize(),
				PageContext.getOffset());
		pageView.setQueryResult(page);
		modelMap.put("sideNavView", pageView);

		return toView(getUrl("manage.indexUI"), modelMap);
	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public ModelAndView welcomeUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));
		return toView(getUrl("manage.welcomeUI"), modelMap);
	}

	@RequestMapping(value = "sideNav.do", method = RequestMethod.GET)
	public Msg sideNav(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				orderby.put("sort", "asc");
				orderby.put("pid", "asc");
				PageContext.setPageSize(Integer.MAX_VALUE);
				Page page = menuService.findChildren(getJsonObject(), orderby);
				PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
						PageContext.getPageSize(), PageContext.getOffset());
				pageView.setQueryResult(page);

				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("sideNavView", pageView);

				modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
				modelMap.put("virtualPath",
						SysConfigUtil.get("html.virtualPath"));

				msg.setData(toHtml(getUrl("manage.side.navUI"), modelMap));
				msg.setSuccess(true);
			}
		});
	}

	/**
	 * 获取二级子菜单
	 *
	 * @return
	 */
	private Page getChildMenus() {
		orderby.put("pid", "asc");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pid", getFirstMenuId());
		Page page = menuService.findChildren(paramMap, orderby);
		return page;
	}

	/**
	 *
	 * @return
	 */
	private String getFirstMenuId() {
		return "02";
	}
}
