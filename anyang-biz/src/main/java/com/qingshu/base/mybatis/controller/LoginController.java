package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSLog;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.plugin.weather.SimpleWeather;
import com.qingshu.common.plugin.weather.WeatherUtils;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.common.util.DateUtils;
import com.qingshu.common.util.LunarCalendar;
import com.qingshu.common.util.PasswordUtil;
import com.qingshu.core.springmvc.authority.FireAuthority;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * 
 * 类描述：系统登陆
 */
@Controller
public class LoginController extends MbsBaseController {
	
	/**
	 * 后台登录页面跳转
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, ModelMap modelMap) {
		return "login";
	}

	/**
	 * 用户名密码检测
	 * 
	 * @param request
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/check.do")
	public @ResponseBody
	AjaxJson check(HttpServletRequest request, JSUser jUser, ModelMap modelMap, AjaxJson j) {
		modelMap.put("userName", jUser.getUserName());
		modelMap.put("passWord", PasswordUtil.encrypt(jUser.getPassWord(), PasswordUtil.Salt, PasswordUtil.getStaticSalt()));
		jUser = mbsCommonService.selectOne("JSUser.list", modelMap);
		if (jUser != null) {
			if(jUser.getUserLevel()==5){
				Object obj = mbsCommonService.selectOne("JGCompany.find", jUser.getId());
				SessionInfo.setSessionInfo(obj, SessionInfo.ADMIN_SESSION);
			}else{
				Object obj = mbsCommonService.selectOne(jUser.getUserType() + ".find", jUser.getId());
				SessionInfo.setSessionInfo(obj, SessionInfo.ADMIN_SESSION);
			}
			//查询当前用的角色
			String roles = "";
			Set<JSRole> jsRoles = jUser.getJsRoles();
			Iterator<JSRole> it = jsRoles.iterator();
			while(it.hasNext()) {
				JSRole jsRole = it.next();
				if(jsRole != null) {
					roles = roles + "," + jsRole.getRoleName();
				}
			}
			if(roles.length() > 0) {
				roles = roles.substring(1);
			}
			ContextUtils.getSession().setAttribute("userRole", roles);
			
			List<JSLog> lgoLists =  mbsCommonService.selectList("JSLog.findLastLogin",jUser.getId());
			if(lgoLists != null && lgoLists.size() > 0) {
				SessionInfo.setAttribute("http://ptlogin2.qq.com/jump?uin=703773128&skey=@gAUINa7UA&u1=http%3A%2F%2Fuser.qzone.qq.com%2F703773128%2Finfocenter%3Fqz_referrer%3DqqtipsjsLog", lgoLists.get(0));
				mbsCommonService.insertLog("登录系统-"+jUser.getNickName(),Global.JSUser,"login");
			}
			j.setInfo("登陆成功!", "y");
		} else {
			j.setInfo("用户名或密码错误!", "n");
		}
		return j;
	}
	/**
	 * 系统退出
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/loginout.do")
	public String loginout(HttpSession session) {
		SessionInfo.removeSessionInfo(SessionInfo.ADMIN_SESSION);
		return "login";
	}
	/**
	 * 系统解锁
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/unlock.do")
	@ResponseBody
	public AjaxJson unlock(AjaxJson j,String password) {
		JSUser jUser=SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		password=PasswordUtil.encrypt(password, PasswordUtil.Salt, PasswordUtil.getStaticSalt());
		if(jUser.getPassWord().equals(password))
		{
			jUser.setIsLock(Global.JSUSer_Unlock);
			mbsCommonService.updateLock(jUser);
			j.setInfo("解锁成功");
		}
		else {
			j.setSuccess(false);
			j.setInfo("解锁密码错误");
		}
		return j;
	}
	/**
	 * 系统锁定
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/lock.do")
	@ResponseBody
	public AjaxJson lock(AjaxJson j) {
		JSUser jUser=SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		jUser.setIsLock(Global.JSUSer_Lock);
		mbsCommonService.updateLock(jUser);
		return j;
	}
	/**
	 * 检查系统是否锁定
	 */
	@FireAuthority(verifyLogin = false, verifyURL = false)
	@RequestMapping("/checklock.do")
	@ResponseBody
	public AjaxJson checklock(AjaxJson j) {
		JSUser jUser=SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		jUser=mbsCommonService.selectOne("JSUser.find",jUser.getId());
		if(Global.JSUSer_Unlock.equals(jUser.getIsLock()))
		{
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 系统首页
	 * 
	 * @param request
	 */
	@RequestMapping("/home.do")
	public String home(ModelMap model, FilterHandler filterHandler) {
		
		Map<String, Object> pramMap = new HashMap<String, Object>();
		Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
		pramMap.put("dateDetail", DateUtils.DateTodstr(cal.getTime())); //昨日
		return "home";
	}
	/**
	 * iframe转向页面
	 * 
	 * @param request
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		SimpleWeather weather = WeatherUtils.getSimepleWeather("101180101");
		modelMap.addAttribute("weather", weather);
		String lunarDate = LunarCalendar.getlunarDate();
		modelMap.addAttribute("lunarDate", lunarDate);
		return "index";
	}

	/**
	 * 框架集页面y
	 * 
	 * @param request
	 */
	@RequestMapping("/main.do")
	public String main(HttpServletRequest request, ModelMap modelMap) {
		return "main";
	}

	/**
	 * 框架顶部页面
	 * 
	 * @param request
	 */
	@RequestMapping("/top.do")
	public String top(HttpServletRequest request, ModelMap modelMap) {
		return "top";
	}

	/**
	 * 框架折叠页面
	 * 
	 * @param request
	 */
	@RequestMapping("/center.do")
	public String center(HttpServletRequest request, ModelMap modelMap) {
		return "center";
	}

	/**
	 * 框架底部页面
	 * 
	 * @param request
	 */
	@RequestMapping("/down.do")
	public String down(HttpServletRequest request, ModelMap modelMap) {
		return "down";
	}

	/**
	 * 框架右侧页面
	 * 
	 * @param request
	 */
	@RequestMapping("/right.do")
	public String right(HttpServletRequest request, ModelMap modelMap) {
		return "right";
	}

	/**
	 * 框架左侧页面
	 * 
	 * @param request
	 */
	@RequestMapping("/left.do")
	public String left(HttpServletRequest request, ModelMap modelMap) {
		return "left";
	}
	
}
