package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.base.vo.UserOrganization;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.DateUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(机构管理)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsorganization")
public class JSOrganizationController extends MbsBaseController {

	/**
	 * 机构列表
	 */
	@RequestMapping("/list.do")
	public String list(JSOrganization jsOrganization, FilterHandler filterHandler, Model model) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.getPagerInfo().setPageSize(200);
		cq.cn("organizationName", jsOrganization.getOrganizationName());
		cq.order("organizationCode", Sort.asc);
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.pagerList", pagerInfo);
		model.addAttribute("list", list);
		return "jsorganization/list";
	}


	/**
	 * 树形下拉框
	 */
	@RequestMapping(value = "tree.do")
	@ResponseBody
	public List<TreeModel> tree(String id, String userId, FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "organizationPid", "organizationName","childOrganizations");
		List<JSOrganization> organizations = ObjectUtils.getArrayList();
		if (!ObjectUtils.isEmpty(userId)) {
			organizations = mbsCommonService.findOrganByUser(userId);
		}
		if (ObjectUtils.isEmpty(id)) {
			id = "0";
		}
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.eq("organizationPid", id);
		cq.order("organizationCode",Sort.asc);
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.pagerList", pagerInfo);
		treeModels = TreeUtil.getTreeList(list, treeModelParm, organizations);
		return treeModels;
	}

	/**
	 * 机构添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add(Model model,String dateTime,JSOrganization organization) {
		Short level = ConvertUtils.getShort(ObjectUtils.getParameter("level"));
		dateTime = DateUtils.getStrDate() ;
		model.addAttribute("level", level);
		model.addAttribute("organization", organization);
		return "jsorganization/add";
	}

	/**
	 * 机构修改页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSOrganization organization = mbsCommonService.selectOne("JSOrganization.findById", id) ;
		model.addAttribute("organization", organization) ;
		return "jsorganization/edit" ;
	}

	/**
	 * 更新机构
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j, JSOrganization jsOrganization) {
		String organizationPid = ObjectUtils.getString(jsOrganization.getOrganizationPid().getId(), "0");
		jsOrganization.getOrganizationPid().setId(organizationPid);
		j= mbsCommonService.update("JSOrganization.update", jsOrganization);
		return j;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSOrganization jsOrganization,Model model) {
		String organizationCode = "10";
		String organizationPid = ObjectUtils.getString(jsOrganization.getOrganizationPid().getId(), "0");
		if (ObjectUtils.isNotEmpty(jsOrganization.getOrganizationPid().getId())) {
			organizationCode = mbsCommonService.selectOne("JSOrganization.createCode", organizationPid);
			if (ObjectUtils.isEmpty(organizationCode)) {
				JSOrganization organization = mbsCommonService.selectOne("JSOrganization.findById", organizationPid);
				organizationCode = organization.getOrganizationCode() + "10";
			}

		} else {
			organizationCode = mbsCommonService.selectOne("JSOrganization.createCode", organizationPid);
		}
		Short organizationLevel = (short) (organizationCode.length() / 2);
		jsOrganization.setOrganizationLevel(organizationLevel);
		jsOrganization.getOrganizationPid().setId(organizationPid);
		jsOrganization.setOrganizationCode(organizationCode);
		j = mbsCommonService.insert("JSOrganization.save", jsOrganization);
		return j;
	}

	/**
	 * 删除机构
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		Short level = ConvertUtils.getShort(ObjectUtils.getParameter("level"));
		j = mbsCommonService.delete("JSOrganization.deleteAll", getIds(ids));
		if (level==2) {
			j.setHref("areaorganlist.do");
		}
		return j;
	}

	/**
	 * 用户分配页面
	 */
	@RequestMapping("allotUser.do")
	public String allotUser(Model model, String id) {
		JSOrganization organization = mbsCommonService.selectOne("JSOrganization.findById", id);
		PagerInfo pagerInfo = new PagerInfo();
		pagerInfo.setSimplePager(false);
		Map<String, Object> queryParams = pagerInfo.getQueryParams();
		queryParams.put("id", id);

		List<JSUser> list = mbsCommonService.selectList("JSOrganization.selectNoallotUser", pagerInfo);
		model.addAttribute("list", list);
		model.addAttribute("organization", organization);
		return "jsorganization/allotUser";
	}

	/**
	 * 用户分配保存
	 */
	@RequestMapping("saveAllot.do")
	@ResponseBody
	public AjaxJson saveAllot(String[] ids, String id, AjaxJson j) {
		List<UserOrganization> list = ObjectUtils.getArrayList();
		for (String uid : ids) {
			UserOrganization userOrganization = new UserOrganization();
			userOrganization.setOrganizationId(id);
			userOrganization.setUserId(uid);
			list.add(userOrganization);
		}
		j.setInfo("分配成功");
		j.setHref("allotUser.do?id=" + id);
		mbsCommonService.batchInsert("JSOrganization.batchAllotUser", list);
		return j;
	}

	/**
	 * 机构已拥有的用户
	 */
	@RequestMapping(value = "allotedUser.do")
	@ResponseBody
	public List<TreeModel> allotedUser(String id, ModelMap model) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "userName", "realName");
		List<JSUser> list = mbsCommonService.findUserByOrgan(id);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}

	/**
	 * 用户分配删除
	 */
	@RequestMapping("deleteAllot.do")
	@ResponseBody
	public AjaxJson deleteAllot(String[] ids, String id, AjaxJson j) {
		Map<String, Object> params = ObjectUtils.getHashMap();
		params.put("id", id);
		params.put("ids", ids);
		mbsCommonService.delete("JSOrganization.deleteAllotUser", params);
		j.setInfo("删除成功");
		j.setHref("allotUser.do?id=" + id);
		return j;
	}

	/**
	 * 高级查询页面跳转
	 */
	@RequestMapping("search.do")
	public String search() {
		return "jsorganization/search";
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
		List<JSUser> jsUsers = mbsCommonService.findUserByOrgan(id);
		if (ObjectUtils.isEmpty(jsUsers)) {
			return true;
		} else {
			return false;
		}

	}
}
