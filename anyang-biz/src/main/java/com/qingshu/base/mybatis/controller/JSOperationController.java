package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSOperation;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(操作管理)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsoperation")
public class JSOperationController extends MbsBaseController {

	/**
	 * 操作列表
	 */
	@RequestMapping("/list.do")
	public String list(JSOperation jsOperation,FilterHandler filterHandler, Model model) {
		PagerInfo pagerInfo=new PagerInfo(filterHandler);
		//Map<String,Object> queryParams=pagerInfo.getQueryParams();
		//queryParams.put("userName","admin");
		CriteriaQuery cq=new CriteriaQuery(pagerInfo,GroupOp.and);
		cq.eq("operationName",jsOperation.getOperationName());
		List<JSRole> list = mbsCommonService.selectList("JSOperation.pagerList",pagerInfo);
		model.addAttribute("list",list);
		return "jsoperation/list";
	}
	/**
	 * 操作添加页面跳转
	 */
	@RequestMapping("/add.do")  
	public String add() {
		return "jsoperation/add";
	}

	/**
	 * 保存操作
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSOperation jsOperation) {
		mbsCommonService.insert("JSOperation.save", jsOperation);
		j.setHref("list.do");
		j.setInfo("添加成功");
		return j;
	}

	/**
	 * 删除操作
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		mbsCommonService.delete("JSOperation.deleteAll", getIds(ids));
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
