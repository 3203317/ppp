package com.qingshu.base.hibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.plugin.log.LogAnnotation;
import com.qingshu.core.hibernate.service.HbmCommonService;
import com.qingshu.core.springmvc.authority.FireAuthority;

/**
 * 
 * 类描述：系统登陆
 */
@Controller
@RequestMapping("/hlogin")
public class HbmLoginController {
	
	@Autowired
	private HbmCommonService hbmCommonService;


	/**
	 * 后台登录页面跳转
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin=false,verifyURL=false)
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, ModelMap modelMap) {
		return "qingshu/login";
	}

	/**
	 * 用户名密码检测
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin=false,verifyURL=false)
	@LogAnnotation(logDesc = "用户{0}登陆")
	@RequestMapping("/check.do")
	public @ResponseBody AjaxJson check(HttpServletRequest request, JSUser jUser,
			ModelMap modelMap, AjaxJson j) {
		modelMap.put("userName", jUser.getUserName());
		modelMap.put("passWord", jUser.getPassWord());
		jUser = hbmCommonService.findByExample(jUser);
		if (jUser != null) {
			SessionInfo.setSessionInfo(jUser,SessionInfo.ADMIN_SESSION);
			j.setInfo("登陆成功","y");
		} else {
			j.setInfo("用户名或密码错误","n");
		}
		return j;
	}

	/**
	 * iframe转向页面
	 * 
	 * @param request
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		return "blue/index";
	}

	/**
	 * 框架集页面
	 * 
	 * @param request
	 */
	@RequestMapping("/hmain.do")
	public String main(HttpServletRequest request, ModelMap modelMap) {
		return "blue/main";
	}

	/**
	 * 框架顶部页面
	 * 
	 * @param request
	 */
	@RequestMapping("/top.do")
	public String top(HttpServletRequest request, ModelMap modelMap) {
		return "blue/top";
	}

	/**
	 * 框架折叠页面
	 * 
	 * @param request
	 */
	@RequestMapping("/center.do")
	public String center(HttpServletRequest request, ModelMap modelMap) {
		return "blue/center";
	}

	/**
	 * 框架底部页面
	 * 
	 * @param request
	 */
	@RequestMapping("/down.do")
	public String down(HttpServletRequest request, ModelMap modelMap) {
		return "blue/down";
	}

	/**
	 * 框架右侧页面
	 * 
	 * @param request
	 */
	@RequestMapping("/right.do")
	public String right(HttpServletRequest request, ModelMap modelMap) {
		return "blue/right";
	}

	/**
	 * 框架左侧页面
	 * 
	 * @param request
	 */
	@RequestMapping("/left.do")
	public String left(HttpServletRequest request, ModelMap modelMap) {
		return "blue/left";
	}
	/**
	 * 后台登录页面跳转
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin=false,verifyURL=false)
	@RequestMapping("/index2.do")
	public String index2(HttpServletRequest request, ModelMap modelMap) {
		return "test/index";
	}
	/**
	 * 后台登录页面跳转
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin=false,verifyURL=false)
	@RequestMapping("/center2.do")
	public String center2(HttpServletRequest request, ModelMap modelMap) {
		return "test/center";
	}
	/**
	 * 后台登录页面跳转
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin=false,verifyURL=false)
	@RequestMapping("/list2.do")
	public String list2(HttpServletRequest request, ModelMap modelMap) {
		return "test/list";
	}
}
