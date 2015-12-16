package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSLog;
import com.qingshu.base.pojo.JSTable;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * Controller of JSLog
 * 
 * @author jsgx
 * @date 2013-9-5
 */
@Controller
@RequestMapping("/jslog")
public class JSLogController extends MbsBaseController {
	/**
	 * 日志列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, FilterHandler filterHandler,JSLog jsLog) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		cq.eq("entityName", jsLog.getEntityName());
		cq.cn("u.nickName", jsLog.getEntityPk());
		cq.bw("methodName",jsLog.getMethodName(),false);
		cq.order("logDate", Sort.desc);
		cq.cn("l.userName",jsLog.getUserName());
		model.addAttribute("jsLog", jsLog);
		List<JSLog> list = mbsCommonService.selectList("JSLog.pagerList", cq.getPagerInfo());
		List<JSTable> tables=mbsCommonService.selectList("JSTable.queryAll");
		//获取所有企业
		model.addAttribute("list", list);
		model.addAttribute("tableList", tables);
		return "jslog/list";
	}
	/**
	 * 日志详细列表
	 */
	@RequestMapping("/logdetail.do")
	public String logdetail(Model model, FilterHandler filterHandler,String logId) {
		// 分页显示指标参数
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		cq.eq("logId",logId);
		List<JSLog> list = mbsCommonService.selectList("JSLogDetail.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		return "jslog/logdetail";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson add(AjaxJson j, JSLog jSLog) {
		mbsCommonService.insert("JSLog.save", jSLog);
		j.setHref("list.do");
		j.setInfo("添加成功");
		return j;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson update(AjaxJson j, JSLog jSLog) {
		mbsCommonService.delete("JSLog.update", jSLog);
		j.setInfo("修改成功");
		j.setHref("list.do");
		return j;
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(AjaxJson j, String[] ids) {
		mbsCommonService.delete("JSLog.deleteAll", getIds(ids));
		j.setInfo("删除成功");
		j.setHref("list.do");
		return j;
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
