package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSMenu;
import com.qingshu.base.pojo.JSPermission;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.base.vo.UserRole;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ResourceUtil;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(角色管理)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsrole")
public class JSRoleController extends MbsBaseController {

	/**
	 * 角色列表
	 */
	@RequestMapping("/list.do")
	public String list(JSRole jsRole, FilterHandler filterHandler, Model model) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.eq("roleName", jsRole.getRoleName());
		cq.order("roleCode", Sort.asc);
		List<JSRole> list = mbsCommonService.selectList("JSRole.pagerList", pagerInfo);
		model.addAttribute("list", list);
		return "jsrole/list";
	}

	/**
	 * 角色添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "jsrole/add";
	}

	/**
	 * 角色修改页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSRole role = mbsCommonService.selectOne("JSRole.findById", id);
		model.addAttribute("role", role);
		return "jsrole/edit";
	}

	/**
	 * 保存角色
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j,JSRole jsRole) {
		String roleCode = "10";
		String rolePid = "0";
		if (ObjectUtils.isNotEmpty(rolePid)) {
			roleCode = mbsCommonService.selectOne("JSRole.createCode", rolePid);
			if (ObjectUtils.isEmpty(roleCode)) {
				JSRole role = mbsCommonService.selectOne("JSRole.findById", rolePid);
				roleCode = role.getRoleCode() + "10";
			}

		} else {
			roleCode = mbsCommonService.selectOne("JSRole.createCode", rolePid);
		}
		jsRole.setRoleCode(roleCode);
		mbsCommonService.insert("JSRole.save", jsRole);
		j.setInfo("角色保存成功");
		return j;
	}

	/**
	 * 更新角色
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j, JSRole jsRole) {
		return mbsCommonService.update("JSRole.update", jsRole);
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		j = mbsCommonService.delete("JSRole.deleteAll", getIds(ids));
		return j;
	}

	/**
	 * 分配权限页面跳转
	 */
	@RequestMapping("/permission.do")
	public String list(String ownerId, Model model,String otype , FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		if(StringUtils.isEmpty(otype)){
			cq.cn("otype", otype);
		}
		cq.order("menuCode", Sort.asc);
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList", pagerInfo);
		JSRole role = mbsCommonService.selectOne("JSRole.findById", ownerId);
		model.addAttribute("list", list);
		model.addAttribute("role", role);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		return "jsrole/permission";
	}

	/**
	 * 保存权限
	 */
	@RequestMapping("/savePermission.do")
	@ResponseBody
	public AjaxJson savePermission(AjaxJson j, String permissions, String roleId) {
		mbsCommonService.savePermission(permissions, roleId, Global.JSRole);
		j.setHref("list.do");
		j.setInfo("权限分配成功");
		return j;
	}

	/**
	 * 角色拥有的用户
	 */
	@RequestMapping(value = "allotedUser.do")
	@ResponseBody
	public List<TreeModel> allotedUser(String id, ModelMap model) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "userName", "realName");
		List<JSUser> list = mbsCommonService.findUserByRole(id);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}

	/**
	 * 用户分配页面
	 */
	@RequestMapping("allotUser.do")
	public String allot(Model model, String id) {
		JSRole role = mbsCommonService.selectOne("JSRole.findById", id);
		PagerInfo pagerInfo = new PagerInfo();
		pagerInfo.setSimplePager(false);
		Map<String, Object> queryParams = pagerInfo.getQueryParams();
		queryParams.put("id", id);
		List<JSUser> list = mbsCommonService.selectList("JSRole.selectNoallotUser", pagerInfo);
		model.addAttribute("list", list);
		model.addAttribute("role", role);
		return "jsrole/allotuser";
	}

	/**
	 * 用户分配保存
	 */
	@RequestMapping("saveAllot.do")
	@ResponseBody
	public AjaxJson saveAllot(String[] ids, String id, AjaxJson j) {
		List<UserRole> list = ObjectUtils.getArrayList();
		for (String uid : ids) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(id);
			userRole.setUserId(uid);
			list.add(userRole);
		}
		j.setInfo("分配成功");
		j.setHref("allotUser.do?id=" + id);
		mbsCommonService.batchInsert("JSRole.batchAllotUser", list);
		return j;
	}

	/**
	 * 用户分配删除
	 */
	@RequestMapping("deleteAllot.do")
	@ResponseBody
	public AjaxJson deleteAllot(String[] ids, String id, AjaxJson j) {
		mbsCommonService.deleteAllotUser(j, id, ids);
		j.setInfo("操作成功");
		j.setHref("allotUser.do?id=" + id);
		return j;
	}

	/**
	 * 树形下拉框
	 */
	@RequestMapping(value = "tree.do")
	@ResponseBody
	public List<TreeModel> tree(String id, String userId, FilterHandler filterHandler) {
		Short level = ConvertUtils.getShort(ObjectUtils.getParameter("level"));/* 角色级别 */
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "rolePid", "roleName");
		List<JSRole> roles = ObjectUtils.getArrayList();
		if (!ObjectUtils.isEmpty(userId)) {
			roles = mbsCommonService.findRoleByUser(userId);
		}
		if (ObjectUtils.isEmpty(id)) {
			id = "0";
		}
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.eq("rolePid", id);
		cq.eq("roleLevel", level);
		List<JSRole> list = mbsCommonService.selectList("JSRole.pagerList", pagerInfo);
		treeModels = TreeUtil.getTreeList(list, treeModelParm, roles);
		return treeModels;
	}

	/**
	 * 高级查询页面跳转
	 */
	@RequestMapping("search.do")
	public String search() {
		return "jsrole/search";
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
		JSRole role = mbsCommonService.selectOne("JSRole.findById", id);
		List<JSUser> jsUsers = mbsCommonService.findUserByRole(id);
		List<JSPermission> jsPermissions = mbsCommonService.findPermissionByRole(id);
		if (ObjectUtils.isEmpty(jsUsers) && ObjectUtils.isEmpty(jsPermissions) && role.getAllowDelete().equals(Global.Delete_Allow)) {
			return true;
		} else {
			return false;
		}
	}
}
