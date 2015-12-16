package com.qingshu.base.mybatis.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSMenu;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.FileUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ResourceUtil;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(菜单管理)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsmenu")
public class JSMenuController extends MbsBaseController {

	/**
	 * 菜单列表
	 */
	@RequestMapping("/list.do")
	public String list(JSMenu jsMenu, FilterHandler filterHandler, Model model) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.eq("menuName", jsMenu.getMenuName());
		cq.order("menuCode", Sort.asc);
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList", pagerInfo);
		model.addAttribute("list", list);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		return "jsmenu/list";
	}

	/**
	 * 树形下拉框
	 */
	@RequestMapping(value = "tree.do")
	@ResponseBody
	public List<TreeModel> tree(String id, ModelMap model) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "menuPid", "menuName");
		List<JSMenu> list;
		if (ObjectUtils.isEmpty(id)) {
			list = mbsCommonService.selectList("JSMenu.findFirstMenu");
			treeModels = TreeUtil.getTreeList(list, treeModelParm);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			list = mbsCommonService.selectList("JSMenu.findChildren", id);
			treeModels = TreeUtil.getTreeList(list, treeModelParm);
		}
		return treeModels;
	}

	/**
	 * 菜单添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "jsmenu/add";
	}

	/**
	 * 菜单编辑页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSMenu menu = mbsCommonService.selectOne("JSMenu.find", id);
		model.addAttribute("menu", menu);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		return "jsmenu/edit";
	}

	/**
	 * 保存菜单
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSMenu jsMenu) {
		String menuCode = "10";
		String menuPid = ObjectUtils.getString(jsMenu.getMenuPid().getId(), "0");
		if (ObjectUtils.isNotEmpty(jsMenu.getMenuPid().getId())) {
			menuCode = mbsCommonService.selectOne("JSMenu.createCode", menuPid);
			if (ObjectUtils.isEmpty(menuCode)) {
				JSMenu menu = mbsCommonService.selectOne("JSMenu.find", menuPid);
				menuCode = menu.getMenuCode() + "10";
			}
			jsMenu.setMenuLevel(Global.SecondMenu);

		} else {
			menuCode = mbsCommonService.selectOne("JSMenu.createCode", menuPid);
			jsMenu.setMenuLevel(Global.FirstMenu);
		}
		jsMenu.getMenuPid().setId(menuPid);
		jsMenu.setMenuCode(menuCode);
		return mbsCommonService.insert("JSMenu.save", jsMenu);
	}

	/**
	 * 更新菜单
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j, JSMenu jsMenu) {
		String menuCode = "10";
		String menuPid = ObjectUtils.getString(jsMenu.getMenuPid().getId(), "0");
		JSMenu oldMenu = mbsCommonService.selectOne("JSMenu.find", jsMenu.getId()); //数据库中记录
		String oldMenuPid = "";
		if (oldMenu.getMenuPid() == null) {
			oldMenuPid = "0";
		} else {
			oldMenuPid = oldMenu.getMenuPid().getId();
		}
		//判断上级菜单是否发生改变，如果发生改变则更新编码
		if(!oldMenuPid.equals(menuPid)) { //发生改变
			if (ObjectUtils.isNotEmpty(menuPid)) {
				menuCode = mbsCommonService.selectOne("JSMenu.createCode", menuPid);
				if (ObjectUtils.isEmpty(menuCode)) {
					JSMenu menu = mbsCommonService.selectOne("JSMenu.find", menuPid);
					menuCode = menu.getMenuCode() + "10";
				}
				jsMenu.setMenuLevel(Global.SecondMenu);

			} else {
				menuCode = mbsCommonService.selectOne("JSMenu.createCode", menuPid);
				jsMenu.setMenuLevel(Global.FirstMenu);
			}
			jsMenu.setMenuCode(menuCode);
			jsMenu.getMenuPid().setId(menuPid);
		} else {
			jsMenu.setMenuCode(oldMenu.getMenuCode());
			jsMenu.getMenuPid().setId(oldMenuPid);
		}
		return mbsCommonService.update("JSMenu.update", jsMenu);
	}

	/**
	 * 删除菜单
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		return mbsCommonService.delete("JSMenu.deleteAll", getIds(ids));
	}

	/**
	 * 高级查询页面跳转
	 */
	@RequestMapping("search.do")
	public String search() {
		return "jsmenu/search";
	}

	/**
	 * 图片选择页面
	 */
	@RequestMapping("images.do")
	public String images(PagerInfo pagerInfo, Model model) {
		String imgPath = ResourceUtil.getConfigByName("Icon_BasePath");
		String path = ContextUtils.getSession().getServletContext().getRealPath(imgPath);
		pagerInfo.setPageSize(48);
		List<File> list = FileUtils.getFileList1(path, pagerInfo);
		model.addAttribute("list", list);
		model.addAttribute("imgPath", imgPath);
		return "jsmenu/images";
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
		JSMenu menu = mbsCommonService.selectOne("JSMenu.find", id);
		if (menu.getAllowDelete().equals(Global.Delete_Allow)) {
			AjaxJson j = mbsCommonService.deleteMenuPermission(id, Global.JSMenu);
			if (j.isSuccess()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
}
