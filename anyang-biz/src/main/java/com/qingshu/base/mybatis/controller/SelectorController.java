package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(选择器)
 */
@Controller
@RequestMapping("/selector")
public class SelectorController extends MbsBaseController {
	/**
	 * 用户选择器主页面
	 */
	@RequestMapping("/usermain.do")
	public String usermain(String param,Model model) {
		model.addAttribute("param",param);
		return "selector/userselector/main";
	}
	/**
	 * 用户选择器左侧树页面
	 */
	@RequestMapping("/userleft.do")
	public String userleft() {
		return "selector/userselector/left";
	}
	/**
	 * 用户选择器列表页面
	 */
	@RequestMapping("/userlist.do")
	public String list(JSUser jsUser, FilterHandler filterHandler, Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		String organizationId=ObjectUtils.getParameter("nodeId");
		String organizationCode="";
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		else {
			JSOrganization organization = mbsCommonService.findOneOrganByUser();
			organizationCode=organization.getOrganizationCode();
		}
		cq.eq("userName", jsUser.getUserName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSUser.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		return "selector/userselector/list";
	}
	/**
	 * 用户选择器左侧页面数据请求
	 */
	@RequestMapping("/usertree.do")
	@ResponseBody
	public List<TreeModel> usertree(String id,String userId) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "organizationPid","organizationName","childOrganizations");
		treeModelParm.setUrlValue("userlist.do");
		treeModelParm.setTargetValue("userMain");
		Map<String, Object> map = new HashMap<String, Object>();
		if(ObjectUtils.isEmpty(id)) {
			JSOrganization organization = mbsCommonService.findOneOrganByUser();
			
			if(ObjectUtils.isEmpty(organization.getOrganizationPid()))
			{
				id="0";
				map.put("organizationPid", id);
			}
			else {
				id=organization.getId();
				map.put("id", id);
			}
			
		}
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.selectByMap", map);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping("/upload.do")
	public String upload(String param,String swfId,Model model) {
		model.addAttribute("param",param);
		model.addAttribute("swfId",swfId);
		return "selector/upload/upload";
	}

	


	
}
