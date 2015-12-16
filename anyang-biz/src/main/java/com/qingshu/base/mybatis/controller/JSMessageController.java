package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qingshu.base.pojo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSMessage;
import com.qingshu.base.pojo.JSMessageReceiver;
import com.qingshu.base.pojo.JSMessageSend;
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
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.DateUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * 个人消息
 */
@Controller
@RequestMapping("/jsmessage")
public class JSMessageController extends MbsBaseController {

	/**
	 * 个人发件箱
	 */
	
	@RequestMapping("/list.do")
	public String list(Model model, FilterHandler filterHandler, JSMessageSend jsMessageSend,String beginDate,String endDate) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		if(ObjectUtils.isNotEmpty(beginDate)){
			cq.gt("sendTime", DateUtils.tstrToTimestamp(beginDate+" 00:00:00"));
			model.addAttribute("beginDate",beginDate);
		}
		if(ObjectUtils.isNotEmpty(endDate)){
			cq.lt("sendTime", DateUtils.tstrToTimestamp(endDate+" 23:59:59"));
			model.addAttribute("endDate",endDate);
		}
		if(ObjectUtils.isNotEmpty(jsMessageSend.getJsMessage().getTitle())){
			cq.cn("jsm.title", jsMessageSend.getJsMessage().getTitle().trim());
		}
		if(!ObjectUtils.isEmpty(jsMessageSend.getJsMessage().getMessageType())){
			cq.eq("jsm.messageType",jsMessageSend.getJsMessage().getMessageType());
		}
		cq.eq("jsms.sendUserId",jUser.getId());
		cq.ne("jsms.status", Global.JSMessage_Garbage);
		cq.order("sendTime", Sort.desc);
		List<JSMessageSend> list = mbsCommonService.selectList("JSMessageSend.pagerList", cq.getPagerInfo());
		model.addAttribute("jsMessage", jsMessageSend.getJsMessage());
		model.addAttribute("list", list);
		return "jsmessage/list";
	}
	/**
	 * 个人收件箱
	 */
	@RequestMapping("/receivelist.do")
	public String receivelist(Model model, FilterHandler filterHandler, JSMessageReceiver jsMessageReceiver,String beginDate,String endDate) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		//insertPublicMessage(jUser.getId());
		
		if(ObjectUtils.isNotEmpty(beginDate)){
			cq.gt("receiverTime", DateUtils.tstrToTimestamp(beginDate+" 00:00:00"));
			model.addAttribute("beginDate",beginDate);
		}
		if(ObjectUtils.isNotEmpty(endDate)){
			cq.lt("receiverTime", DateUtils.tstrToTimestamp(endDate+" 23:59:59"));
			model.addAttribute("endDate",endDate);
		}
		cq.cn("jsm.title", jsMessageReceiver.getJsMessage().getTitle());
		if(!ObjectUtils.isEmpty(jsMessageReceiver.getJsMessage().getMessageType())){
			cq.eq("jsm.messageType",jsMessageReceiver.getJsMessage().getMessageType());
		}
		cq.eq("receiverUserId",jUser.getId());
		cq.ne("jsmr.status", Global.JSMessage_Garbage);
		cq.order("receiverTime desc,jsmr.status", Sort.asc);
		List<JSMessageReceiver> list = mbsCommonService.selectList("JSMessageReceiver.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		model.addAttribute("jsMessage", jsMessageReceiver.getJsMessage());
		return "jsmessage/receivelist";
	}
	/**
	 * 垃圾箱
	 */
	@RequestMapping("/garbagelist.do")
	public String garbagelist(Model model, FilterHandler filterHandler, JSMessageReceiver jsMessageReceive,String beginDate,String endDate) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and, model);
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);

		if(ObjectUtils.isNotEmpty(beginDate)){
			cq.gt("receiverTime", DateUtils.tstrToTimestamp(beginDate+" 00:00:00"));
			model.addAttribute("beginDate",beginDate);
		}
		if(ObjectUtils.isNotEmpty(endDate)){
			cq.lt("receiverTime",DateUtils.tstrToTimestamp(endDate+" 23:59:59"));
			model.addAttribute("endDate",endDate);
		}
		cq.cn("jsm.title", jsMessageReceive.getJsMessage().getTitle());
		if(!ObjectUtils.isEmpty(jsMessageReceive.getJsMessage().getMessageType())){
			cq.eq("jsm.messageType",jsMessageReceive.getJsMessage().getMessageType());
		}
		cq.eq("jsmr.receiverUserId",jUser.getId());
		cq.eq("jsmr.status", Global.JSMessage_Garbage);
		cq.order("receiverTime", Sort.desc);
		List<JSMessageReceiver> list = mbsCommonService.selectList("JSMessageReceiver.pagerList", cq.getPagerInfo());
		model.addAttribute("list", list);
		model.addAttribute("jsMessage", jsMessageReceive.getJsMessage());
		return "jsmessage/garbagelist";
	}
	@RequestMapping(value = "/main")
	public String main() {
		return "jsmessage/main";
	}

	@RequestMapping(value = "/left")
	public String left(Model model) {
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		//获取收件箱，发件箱，回收站邮件数目
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", jUser.getId());
		map.put("notstatus", "2");
		//收件箱邮件数目
		int receiveNumber=mbsCommonService.selectOne("JSMessageReceiver.count",map);
		model.addAttribute("receiveNumber", receiveNumber);
		map.remove("notstatus");
		map.put("status", 2);
		//回收站邮件数目
		model.addAttribute("garbageNumber", mbsCommonService.selectOne("JSMessageReceiver.count",map));
		//发件箱邮件数目
		model.addAttribute("sendNumber", mbsCommonService.selectOne("JSMessageSend.count",jUser.getId()));
		return "jsmessage/left";
	}

	@RequestMapping(value = "/addmain")
	public String addmain() {
		return "jsmessage/addmain";
	}
	@RequestMapping(value = "/add")
	public String add() {
		return "jsmessage/add";
	}
	@RequestMapping(value = "/right")
	public String right() {
		return "jsmessage/right";
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public AjaxJson save(JSMessage jsMessage, String userId) {
		AjaxJson j = new AjaxJson();
		j = mbsCommonService.sendMessage(jsMessage);
		j.setInfo("发送成功");
		return j;
	}

	@RequestMapping(value = "/show")
	public String show(String messageId, String id,String flag, Model model) {
		JSMessage jsMessage = mbsCommonService.selectOne("JSMessage.findById", messageId);
		if(StringUtils.hasText(id)){
			JSMessageReceiver jSMessageReceiver = mbsCommonService.selectOne("JSMessageReceiver.findById", id);
			jSMessageReceiver.setStatus(Short.valueOf("1"));
			mbsCommonService.update("JSMessageReceiver.update", jSMessageReceiver);
		}
		model.addAttribute("flag",flag);
		model.addAttribute("message", jsMessage);
		return "jsmessage/show";
	}
	//修改接收表记录状态为已删除（2）
	@RequestMapping(value = "/deleteReceive.do")
	@ResponseBody
	public AjaxJson deleteReceive(AjaxJson j,String[] ids) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", Global.JSMessage_Garbage);
		map.put("ids", getIds(ids));
		mbsCommonService.update("JSMessageReceiver.updateStatus", map);
		j.setHref("receivelist.do");
		j.setInfo("移入成功");
		return j;
	}
	//删除发送记录
	@RequestMapping(value = "/deleteSend.do")
	@ResponseBody
	public AjaxJson deleteSend(AjaxJson j,String[] ids) {
		mbsCommonService.delete("JSMessageSend.deleteByIds", getIds(ids));
		j.setInfo("删除成功");
		return j;
	}
	//修改发送表记录状态为新加（0）
	@RequestMapping(value = "/renew.do")
	@ResponseBody
	public AjaxJson renew(AjaxJson j,String[] ids) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", Global.JSMessage_New);
		map.put("ids", getIds(ids));
		mbsCommonService.update("JSMessageReceiver.updateStatus", map);
		j.setHref("garbagelist.do");
		j.setInfo("恢复成功");
		return j;
	}
	//删除收件箱记录
	@RequestMapping(value = "/deleteReceiverReal.do")
	@ResponseBody
	public AjaxJson deleteSendReal(AjaxJson j,String[] ids) {
		mbsCommonService.delete("JSMessageReceiver.deleteByIds", getIds(ids));
		j.setHref("garbagelist.do");
		j.setInfo("删除成功");
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
	/**
	 * 异步加载人员
	 */
	@RequestMapping(value = "/getUserTree.do")
	@ResponseBody
	public List<TreeModel> getUserTree(String id){
		Map<String,Object> map=new HashMap<String, Object>();
		if(id==null){
			List<TreeModel> list=new ArrayList<TreeModel>();
			//获取所有一级机构
			List<JSOrganization> roots = mbsCommonService.selectList("JSOrganization.findByPid","0");
			if(roots != null && roots.size() > 0) {
				for(JSOrganization root : roots) {
					//转换成树形节点
					TreeModel model=new TreeModel();
					model.setId(root.getId());
					model.setName(root.getOrganizationName());
					model.setIsParent(true);
					model.setNocheck(true);
					list.add(model);
				}
			}
			return list;
		}else{
			map.put("organId", id);
			//获取机构下人员
			List<JSUser> uList=mbsCommonService.selectList("JSOrganization.findUserByOrganAndUserType",map);
			//转换成树形节点
			TreeModelParm treeModelParm=new TreeModelParm("id","","realName");
			List<TreeModel> list=TreeUtil.getTreeList(uList, treeModelParm);
			if(list!=null&&list.size()>0){
				for(TreeModel tm:list){
					Map<String,Object> map1=new HashMap<String, Object>();
					map1.put("icon","../../res/qingshu/images/small_role.png");
					tm.setOtherParam(map1);
					tm.setpId(id);
					tm.setIsParent(false);
				}
			}
			//获取子机构
			map.clear();
			map.put("pid", id);
			List<JSUser> oList=mbsCommonService.selectList("JSOrganization.findByPid",map);
			//转换成树形节点
			treeModelParm=new TreeModelParm("id","","organizationName");
			List<TreeModel> olist=TreeUtil.getTreeList(oList, treeModelParm);
			if(olist!=null&&olist.size()>0){
				for(TreeModel tm:olist){
					tm.setpId(id);
					tm.setIsParent(true);
					tm.setNocheck(true);
				}
			}
			list.addAll(olist);
			return list;
		}
	}
}
