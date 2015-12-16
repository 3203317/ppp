package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSArea;
import com.qingshu.base.pojo.JSMenu;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.PasswordUtil;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(行政区域)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsarea")
public class JSAreaController extends MbsBaseController {

	/**
	 * 行政区域
	 */
	@RequestMapping("/list.do")
	public String list(JSArea jsArea, FilterHandler filterHandler, Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		cq.order("areaCode", Sort.asc);
		List<JSArea> list = mbsCommonService.selectList("JSArea.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		return "jsarea/list";
	}

	/**
	 * 行政区域添加跳转
	 */
	@RequestMapping("/add.do")
	public String add(Model model, FilterHandler filterHandler) {
		return "jsarea/add";
	}
	/**
	 * 行政区域修改跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSArea area = mbsCommonService.selectOne("JSArea.findById", id);
		model.addAttribute("area", area);
		return "jsarea/edit";
	}

	/**
	 * 保存机构
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSArea jsArea) {
		String areaCode = "10";
		String aeraPid = ObjectUtils.getString(jsArea.getAreaParent().getId(), "0");
		if (ObjectUtils.isNotEmpty(jsArea.getAreaParent().getId())) {
			areaCode = mbsCommonService.selectOne("JSArea.createCode", aeraPid);
			if (ObjectUtils.isEmpty(areaCode)) {
				JSArea area = mbsCommonService.selectOne("JSArea.findById", aeraPid);
				areaCode = area.getAreaCode() + "10";
			}

		} else {
			areaCode = mbsCommonService.selectOne("JSArea.createCode", aeraPid);
			if (ObjectUtils.isEmpty(areaCode)) {
				areaCode = "10";
			}
		}
		Short areaLeavel = (short) (areaCode.length() / 2);
		jsArea.setAreaLevel(areaLeavel);
		jsArea.getAreaParent().setId(aeraPid);
		jsArea.setAreaCode(areaCode);
		j = mbsCommonService.insert("JSArea.save", jsArea);
		return j;
	}
	/**
	 * 删除机构
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		j = mbsCommonService.delete("JSArea.deleteAll", getIds(ids));
		return j;
	}

	/**
	 * 更新行政区域
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j,JSArea jsArea) {
		String organizationPid = ObjectUtils.getString(jsArea.getAreaParent().getId(), "0");
		jsArea.getAreaParent().setId(organizationPid);
		j= mbsCommonService.update("JSArea.update", jsArea);
		return j;
	}

	/**
	 * 树形下拉框
	 */
	@RequestMapping(value = "tree.do")
	@ResponseBody
	public List<TreeModel> tree(String id, String userId, FilterHandler filterHandler) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and);
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "areaParent", "areaName","childAreas");
		if (ObjectUtils.isEmpty(id)) {
			id = "0";
		}
		List<JSOrganization> organizations = ObjectUtils.getArrayList();
		cq.eq("areaParentId", id);
		cq.order("areaCode", Sort.asc);
		List<JSOrganization> list = mbsCommonService.selectList("JSArea.pagerList", cq.getPagerInfo());
		treeModels = TreeUtil.getTreeList(list, treeModelParm, organizations);
		return treeModels;
	}


	/**
	 * 企业用户添加页面跳转
	 */
	@RequestMapping("/addcompany.do")
	public String addcompany(Model model, FilterHandler filterHandler, String type) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.order("menuCode", Sort.asc);
		cq.in("menuType", "1");
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList", pagerInfo);
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		return "company/add";
	}

	/**
	 * 企业用户修改页面跳转
	 */
	@RequestMapping("/editcompany.do")
	public String editcompany(String id, Model model, FilterHandler filterHandler, String type) {
		JSUser user = mbsCommonService.selectOne("JSUser.find", id);
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.order("menuCode", Sort.asc);
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList", pagerInfo);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		model.addAttribute("type", type);
		return "company/edit";
	}


	/**
	 * 企业用户列表跳转
	 */
	@RequestMapping("/companymain.do")
	public String companymain() {

		return "company/companymain";
	}

	/**
	 * 企业用户列表跳转
	 */
	@RequestMapping("/companyleft.do")
	public String companyleft() {
		return "company/companyleft";
	}

	/**
	 * 企业用户列表
	 */
	@RequestMapping("/companylist.do")
	public String companylist(JSUser jsUser, FilterHandler filterHandler, Model model) {
		String organizationId = ObjectUtils.getParameter("nodeId");
		String organizationCode = ObjectUtils.getParameter("organizationCode");
		if (ObjectUtils.isNotEmpty(organizationId)) {
			JSOrganization jsOrganization = mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode = jsOrganization.getOrganizationCode();
			model.addAttribute("nodeId", organizationId);
		}
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		cq.cn("nickName", jsUser.getNickName());
		cq.bw("organizationCode", organizationCode);
		//cq.ne("userType", Global.JSUser);
		cq.eq("userType", jsUser.getUserType());
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSUser.pagerList", cq.getPagerInfo());
		JSUser jsuser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		model.addAttribute("jsuser", jsuser);
		model.addAttribute("list", list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationLevel", Global.JSOrgain_2);
		List<JSOrganization> orgList = mbsCommonService.selectList("JSOrganization.selectByMap", map);
		model.addAttribute("orgList", orgList);
		return "company/companylist";
	}


	/**
	 * 办事处企业列表
	 */
	@RequestMapping("/areacompanylist.do")
	public String areacompanylist(JSUser jsUser, FilterHandler filterHandler, Model model) {
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSOrganization jsOrganization = mbsCommonService.findOneOrganByUser(jUser.getId());
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		String organizationCode = "";
		List<JSUser> list = null;
		if (!ObjectUtils.isEmpty(jsOrganization)) {
			organizationCode = jsOrganization.getOrganizationCode();
			CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
			cq.cn("nickName", jsUser.getNickName());
			cq.bw("organizationCode", organizationCode);
			//cq.ne("userType", Global.JSUser);
			cq.eq("userType", jsUser.getUserType());
			cq.groupBy("id");
			list = mbsCommonService.selectList("JSUser.pagerList", pagerInfo);
		}
		model.addAttribute("list", list);
		return "company/list";
	}

	/**
	 * 企业用户左侧树
	 */
	@RequestMapping("/companytree.do")
	@ResponseBody
	public List<TreeModel> companytree(String id, String userId) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "organizationPid", "organizationName");
		treeModelParm.setUrlValue("companylist.do");
		treeModelParm.setTargetValue("Main");
		if (ObjectUtils.isEmpty(id)) {
			id = "0";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationPid", id);
		map.put("organizationLevel", Global.JSOrgain_2);
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.selectByMap", map);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}

	/**
	 * 获取可删除的对象ID
	 */
	private String[] getIds(String[] ids) {
		List<String> idList = new ArrayList<String>();
		for (String id : ids) {
			if (checkId(id)) {
				idList.add(id);
			}
		}
		return ConvertUtils.listToArray(idList);
	}

	/**
	 * 检查对象是否可删除
	 */
	private Boolean checkId(String id) {
		return true;
	}
}
