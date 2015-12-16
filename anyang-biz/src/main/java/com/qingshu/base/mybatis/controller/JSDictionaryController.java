package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSDictionary;
import com.qingshu.base.pojo.JSDictionaryGroup;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.PinyinUtil;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(字典管理)
 */
@Controller
@RequestMapping("/jsdictionary")
public class JSDictionaryController extends MbsBaseController {

	/**
	 * 字典列表
	 */
	@RequestMapping("/list.do")
	public String list(JSDictionary jsDictionary, FilterHandler filterHandler, Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		String nodeId = ObjectUtils.getParameter("nodeId", "0");
		JSDictionaryGroup dictionaryGroup = mbsCommonService.selectOne("JSDictionaryGroup.findById", nodeId);
		List<JSDictionaryGroup> jsDictionaryGroups = mbsCommonService.selectList("JSDictionaryGroup.queryAll");
		if (ObjectUtils.isEmpty(dictionaryGroup)) {
			if (!ObjectUtils.isEmpty(jsDictionaryGroups)) {
				dictionaryGroup = jsDictionaryGroups.get(0);
			}
		}
		cq.eq("dictGroupId",dictionaryGroup.getId());
		List<JSDictionary> list = mbsCommonService.selectList("JSDictionary.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		model.addAttribute("dictionaryGroup", dictionaryGroup);
		return "jsdictionary/list";
	}

	/**
	 * 字典添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add(String id,Model model) {
		model.addAttribute("id", id);
		return "jsdictionary/add";
	}

	/**
	 * 字典添加修改页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSDictionary jsDictionary = mbsCommonService.selectOne("JSDictionary.findById", id);
		model.addAttribute("dictionary", jsDictionary);
		return "jsdictionary/edit";
	}

	/**
	 * 保存操作
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j,JSDictionary jsDictionary) {
		if (ObjectUtils.isEmpty(jsDictionary.getDictCode())) {
			jsDictionary.setDictCode(PinyinUtil.getPinYinHeadChar(jsDictionary.getDictName()));
		}
		mbsCommonService.insert("JSDictionary.save", jsDictionary);
		j.setHref("list.do?nodeId="+jsDictionary.getDictGroup().getId());
		j.setInfo("分组添加成功");
		return j;
	}

	/**
	 * 更新字典分组
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j,JSDictionary jsDictionary) {
		jsDictionary.setDictCode(PinyinUtil.getPinYinHeadChar(jsDictionary.getDictName()));
		mbsCommonService.update("JSDictionary.update", jsDictionary);
		j.setHref("list.do?nodeId="+jsDictionary.getDictGroup().getId());
		j.setInfo("字典分类更新成功");
		return j;
	}

	/**
	 * 删除操作
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids,String id) {
		mbsCommonService.delete("JSDictionary.deleteByIds", getIds(ids));
		j.setInfo("删除成功");
		j.setHref("list.do?nodeId="+id);
		return j;
	}

	/**
	 * 系统字典主页面跳转
	 */
	@RequestMapping("/main.do")
	public String main() {
		return "jsdictionary/main";
	}

	/**
	 * 系统字典左侧树页面
	 */
	@RequestMapping("/left.do")
	public String left() {
		return "jsdictionary/left";
	}

	/**
	 * 系统字典左侧树数据请求地址
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public List<TreeModel> tree(String id, String userId) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "dictGroupName");
		treeModelParm.setUrlValue("list.do");
		treeModelParm.setTargetValue("Main");
		if (ObjectUtils.isEmpty(id)) {
			id = "0";
		}
		List<JSDictionaryGroup> list = mbsCommonService.selectList("JSDictionaryGroup.queryAll");
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
