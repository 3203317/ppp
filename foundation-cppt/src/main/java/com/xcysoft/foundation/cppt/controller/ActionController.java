package com.xcysoft.foundation.cppt.controller;

import java.util.HashMap;
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

import com.xcysoft.foundation.cppt.biz.ActionService;

/**
 *
 * @author huangxin
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/action")
@SuppressWarnings("all")
public class ActionController extends MultiViewResource {

	@Autowired
	private ActionService actionService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));

		Page page = actionService.findList(null);
		PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
				PageContext.getPageSize(), PageContext.getOffset());
		pageView.setQueryResult(page);
		modelMap.put("pageView", pageView);

		return toView(getUrl("action.indexUI"), modelMap);
	}

	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public Msg list(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				Page page = actionService.findList(getJsonObject());
				PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
						PageContext.getPageSize(), PageContext.getOffset());
				pageView.setQueryResult(page);

				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("pageView", pageView);

				msg.setData(toHtml(getUrl("action.indexUI.listUI"), modelMap));
				msg.setSuccess(true);
			}
		});
	}
}
