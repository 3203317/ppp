package com.xcysoft.foundation.privilege.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.exception.asserts.AssertObject;
import cn.newcapec.framework.core.handler.MultiViewResource;
import cn.newcapec.framework.core.rest.Msg;
import cn.newcapec.framework.core.utils.fileUtils.SysConfigUtil;
import cn.newcapec.framework.core.utils.jsonUtils.JSONTools;
import cn.newcapec.framework.core.utils.pagesUtils.Page;
import cn.newcapec.framework.core.utils.pagesUtils.PageContext;
import cn.newcapec.framework.core.utils.pagesUtils.PageView;

import com.xcysoft.foundation.privilege.biz.UserService;
import com.xcysoft.foundation.privilege.model.User;

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
	 *
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addUserUI() {
		User user = userService.get("12");
		System.out.println(user.getUser_name());
		return getUrl("user.addUserUI");
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	@ResponseBody
	public Msg addUser(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				msg.setSuccess(false);
				msg.setMsg("保存成功");
			}
		});
	}

	/**
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView loginUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));
		return toView(getUrl("user.loginUI"), modelMap);
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		// TODO
		return redirect("login");
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public Msg login(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				User user = JSONTools.JSONToBean(getJsonObject(), User.class);

				String user_name = user.getUser_name();
				String password = user.getPassword();

				if (null == user_name || "".equals(user_name.trim())) {
					msg.setMsg("用户名不能为空");
					return;
				}

				if (null == password || "".equals(password.trim())) {
					msg.setMsg("密码不能为空");
					return;
				}

				Msg result = userService.loginValidate(user_name, password);
				msg.setMsg(result.getMsg());

				if (result.isSuccess()) {
					msg.setSuccess(result.isSuccess());
				}
			}
		});
	}

	/**
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "changePwd", method = RequestMethod.GET)
	public ModelAndView changePwdUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));
		return toView(getUrl("user.changePwdUI"), modelMap);
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexUI(ModelMap modelMap) {
		modelMap.put("cdn", SysConfigUtil.get("html.cdn"));
		modelMap.put("virtualPath", SysConfigUtil.get("html.virtualPath"));

		Page page = userService.findList(null);
		PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
				PageContext.getPageSize(), PageContext.getOffset());
		pageView.setQueryResult(page);
		modelMap.put("pageView", pageView);

		return toView(getUrl("user.indexUI"), modelMap);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Msg list(final HttpServletRequest request) {
		return doExpAssert(new AssertObject() {
			@Override
			public void AssertMethod(Msg msg) {
				Page page = userService.findList(getJsonObject());
				PageView<Map<String, Object>> pageView = new PageView<Map<String, Object>>(
						PageContext.getPageSize(), PageContext.getOffset());
				pageView.setQueryResult(page);

				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("pageView", pageView);

				msg.setData(toHtml(getUrl("user.indexUI.listUI"), modelMap));
				msg.setSuccess(true);
			}
		});

	}
}
