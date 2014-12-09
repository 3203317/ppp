package com.xcysoft.cppt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xcysoft.cppt.biz.UserService;
import com.xcysoft.framework.core.handler.MultiViewResource;
import com.xcysoft.framework.core.utils.pagesUtils.Page;
import com.xcysoft.framework.core.utils.pagesUtils.PageContext;
import com.xcysoft.framework.core.utils.pagesUtils.PageView;

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
	 * 用户管理
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "index")
	public ModelAndView stuListUI(ModelMap modelMap) {
		Page page = userService.querys(getJsonObject());
		// 菜单列表视图
		PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
				PageContext.getPagesize(), PageContext.getOffset());
		pageView.setQueryResult(page);
		pageView.setJsMethod("reloadUserList");
		modelMap.put("pageView", pageView);
		return toView(getUrl("user.index"), modelMap);
	}
}
