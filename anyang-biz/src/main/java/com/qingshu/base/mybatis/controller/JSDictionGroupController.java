package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSDictionaryGroup;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.PinyinUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(操作管理)
 */
@Controller
@RequestMapping("/jsdictiongroup")
public class JSDictionGroupController extends MbsBaseController {

	/**
	 * 字典列表
	 */
	@RequestMapping("/list.do")
	public String list(JSDictionaryGroup jsDictionaryGroup, FilterHandler filterHandler, Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		List<JSDictionaryGroup> list = mbsCommonService.selectList("JSDictionaryGroup.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		return "jsdictiongroup/list";
	}

	/**
	 * 字典添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "jsdictiongroup/add";
	}
	/**
	 * 字典添加修改页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model) {
		JSDictionaryGroup jsDictionaryGroup = mbsCommonService.selectOne("JSDictionaryGroup.findById", id);
		model.addAttribute("dictionaryGroup", jsDictionaryGroup);
		return "jsdictiongroup/edit";
	}

	/**
	 * 保存操作
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSDictionaryGroup jsDictionaryGroup) {
		if (ObjectUtils.isEmpty(jsDictionaryGroup.getDictGroupCode())) {
			jsDictionaryGroup.setDictGroupCode(PinyinUtil.getPinYinHeadChar(jsDictionaryGroup.getDictGroupName()));
		}
		mbsCommonService.insert("JSDictionaryGroup.save", jsDictionaryGroup);
		j.setHref("list.do");
		j.setInfo("分组添加成功");
		return j;
	}
	/**
	 * 更新字典分组
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public AjaxJson update(AjaxJson j, JSDictionaryGroup jsDictionaryGroup) {
		jsDictionaryGroup.setDictGroupCode(PinyinUtil.getPinYinHeadChar(jsDictionaryGroup.getDictGroupName()));
		mbsCommonService.update("JSDictionaryGroup.update", jsDictionaryGroup);
		j.setHref("list.do");
		j.setInfo("分组更新成功");
		return j;
	}

	/**
	 * 删除操作
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		mbsCommonService.delete("JSDictionaryGroup.deleteByIds", getIds(ids));
		j.setInfo("删除成功");
		j.setHref("list.do");
		return j;
	}

	/**
	 * 高级查询页面跳转
	 */
	@RequestMapping("search.do")
	public String search() {
		return "jsoperation/search";
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
