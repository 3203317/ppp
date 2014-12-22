package com.xcysoft.foundation.cppt.controller;

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
import cn.newcapec.framework.core.utils.jsonUtils.JSONTools;

import com.xcysoft.foundation.cppt.biz.UserService;
import com.xcysoft.foundation.cppt.model.User;

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
		modelMap.put("cdn", "http://localhost:1234/js/");
		modelMap.put("virtualPath", "/controller/");
		return toView(getUrl("user.loginUI"), modelMap);
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
				String password = user.getPassword().trim();

				Msg result = userService.loginValidate(user_name, password);
				msg.setMsg(result.getMsg());

				if (result.isSuccess()) {
					msg.setSuccess(result.isSuccess());
				}
			}
		});
	}
}
