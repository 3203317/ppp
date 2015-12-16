package com.qingshu.base.hibernate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.hibernate.qbc.CriteriaQuery;
import com.qingshu.core.springmvc.controller.HbmBaseController;

/**
 * 
 * 类描述：用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController extends HbmBaseController{
	
	/**
	 * 用户列表
	 * 
	 * @param request
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request,FilterHandler filterHandler,Model model,JSUser jsUser) {
		CriteriaQuery cq =new CriteriaQuery(JSUser.class,filterHandler);
		String organizationId=ObjectUtils.getParameter("nodeId");
		String organizationCode="";
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=hbmCommonService.getEntity(JSOrganization.class, organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		cq.eq("userName", jsUser.getUserName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		List<JSUser> list=hbmCommonService.getPagerList(cq, true);
		model.addAttribute("list", list);
		return "jsuser/list";
	}
}
