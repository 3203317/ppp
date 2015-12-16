package com.qingshu.base.mybatis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.base.pojo.JSMenu;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.annotation.Transaction;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.filters.Sort;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;
import com.qingshu.common.json.model.ValidForm;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.PasswordUtil;
import com.qingshu.common.util.ResourceUtil;
import com.qingshu.common.util.TreeUtil;
import com.qingshu.core.springmvc.controller.MbsBaseController;

/**
 * @description:(用户管理)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
@Controller
@RequestMapping("/jsuser")
public class JSUserController extends MbsBaseController {
	/**
	 * 技术支持页面跳转
	 * 
	 */
	@RequestMapping("/technicalSupport.do")
	public String technicalSupport() {
		return "jsuser/technicalSupport";
	}
	/**
	 * 用户管理页面跳转
	 */
	@RequestMapping("/usermain.do")
	public String usermain() {
		return "jsuser/main";
	}
	/**
	 * 办事处用户管理页面跳转
	 */
	@RequestMapping("/busermain.do")
	public String busermain(Model model) {
		
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSOrganization jsorg = mbsCommonService.findOneOrganByUser(jUser.getId());
		if(!ObjectUtils.isEmpty(jsorg)){
			model.addAttribute("orgid",jsorg.getId());
			model.addAttribute("orglevel",jsorg.getOrganizationLevel());
		}
		return "jsuser/bmain";
	}
	/**
	 * 用户管理左侧树页面
	 */
	@RequestMapping("/userleft.do")
	public String userleft() {
		return "jsuser/left";
	}
	/**
	 * 用户管理左侧树页面
	 */
	@RequestMapping("/buserleft.do")
	public String buserleft() {
		return "jsuser/bleft";
	}
	/**
	 * 用户列表
	 */
	@RequestMapping("/list.do")
	public String list(JSUser jsUser, FilterHandler filterHandler,Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and,model);
		String organizationId=ObjectUtils.getParameter("nodeId");
		String organizationCode="";
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		
		cq.eq("userName", jsUser.getUserName());
		cq.cn("realName", jsUser.getRealName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSUser.pagerList",cq.getPagerInfo());
		model.addAttribute("list", list);
		return "jsuser/list";
	}
	/**
	 * 办事处用户列表
	 */
	@RequestMapping("/blist.do")
	public String blist(JSUser jsUser, FilterHandler filterHandler,Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and,model);
		String organizationCode = ObjectUtils.getParameter("organizationCode");
		String organizationId=ObjectUtils.getParameter("nodeId");
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		
		cq.eq("userName", jsUser.getUserName());
		cq.cn("realName", jsUser.getRealName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSUser.pagerList",cq.getPagerInfo());
		model.addAttribute("list", list);
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSOrganization jsorg = mbsCommonService.findOneOrganByUser(jUser.getId());
		if(!ObjectUtils.isEmpty(jsorg)){
			model.addAttribute("orgid",jsorg.getId());
		}
		if(jsorg.getOrganizationLevel()==1){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("organizationLevel", Global.JSOrgain_2);
			List<JSOrganization> orgList = mbsCommonService.selectList("JSOrganization.selectByMap", map);
			model.addAttribute("orgList", orgList);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", jsorg.getId());
			List<JSOrganization> orgList = mbsCommonService.selectList("JSOrganization.selectByMap", map);
			model.addAttribute("orgList", orgList);
		}
			
		
		
		return "jsuser/blist";
	}
	/**
	 * 用户列表
	 */
	@RequestMapping("/xlist.do")
	public String xlist(JSUser jsUser, FilterHandler filterHandler, Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and,model);
		String organizationId=ObjectUtils.getParameter("nodeId");
		String organizationCode="";
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		
		cq.eq("userName", jsUser.getUserName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSOrganization.findUserByOrgan","2");
		model.addAttribute("list", list);
		return "jsuser/xlist";
	}
	/**
	 * 用户列表
	 */
	@RequestMapping("/glist.do")
	public String glist(JSUser jsUser, FilterHandler filterHandler, String oid,Model model) {
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and,model);
		String organizationId=ObjectUtils.getParameter("nodeId");
		String organizationCode="";
		if(ObjectUtils.isNotEmpty(organizationId))
		{
			JSOrganization jsOrganization=mbsCommonService.selectOne("JSOrganization.findById", organizationId);
			organizationCode=jsOrganization.getOrganizationCode();
		}
		
		cq.eq("userName", jsUser.getUserName());
		cq.bw("organizationCode",organizationCode);
		cq.eq("userType",Global.JSUser);
		cq.groupBy("id");
		List<JSUser> list = mbsCommonService.selectList("JSOrganization.findUserByOrgan","4");
		model.addAttribute("list", list);
		return "jsuser/glist";
	}
	/**
	 * 用户左侧树
	 */
	@RequestMapping("/usertree.do")
	@ResponseBody
	public List<TreeModel> usertree(String id,String userId) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "organizationPid","organizationName","childOrganizations");
		treeModelParm.setUrlValue("list.do");
		treeModelParm.setTargetValue("userMain");
		if(ObjectUtils.isEmpty(id)) {
			id="0";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationPid", id);
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.selectByMap", map);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}

	/**
	 * 用户左侧树
	 */
	@RequestMapping("/busertree.do")
	@ResponseBody
	public List<TreeModel> busertree(String id,String userId) {
		List<TreeModel> treeModels = new ArrayList<TreeModel>();
		TreeModelParm treeModelParm = new TreeModelParm("id", "organizationPid","organizationName","childOrganizations");
		treeModelParm.setUrlValue("blist.do");
		treeModelParm.setTargetValue("buserMain");
		if(ObjectUtils.isEmpty(id)) {
			id="0";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationPid", id);
		map.put("organizationLevel", Global.JSOrgain_2);
		List<JSOrganization> list = mbsCommonService.selectList("JSOrganization.selectByMap", map);
		treeModels = TreeUtil.getTreeList(list, treeModelParm);
		return treeModels;
	}


	/**
	 * 用户添加页面跳转
	 */
	@RequestMapping("/add.do")
	public String add(Model model, ModelMap modelMap,FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.order("menuCode",Sort.asc);
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList",pagerInfo);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		model.addAttribute("list", list);
		return "jsuser/add";
	}
	/**
	 * 用户添加页面跳转
	 */
	@RequestMapping("/addgx.do")
	public String addgx(Model model, ModelMap modelMap,String oid,FilterHandler filterHandler) {
		model.addAttribute("oid", oid);
		return "jsuser/addgx";
	}

	/**
	 * 用户添加页面跳转
	 */
	@RequestMapping("/badd.do")
	public String badd(Model model, ModelMap modelMap,String oid,FilterHandler filterHandler) {
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSOrganization jsorg = mbsCommonService.findOneOrganByUser(jUser.getId());
		if(ObjectUtils.isEmpty(jsorg)){
			model.addAttribute("orgid",jsorg.getId());
		}
		
		List<JSOrganization> orgList = mbsCommonService.findOrganByUserIsAdmin();
		if(mbsCommonService.getUserType(jUser)==2){
			orgList.add(jsorg);
		}
		model.addAttribute("orgList", orgList);
		return "jsuser/badd";
	}

	/**
	 * 打开修改密码页面跳转
	 * 
	 * @param request
	 */
	@RequestMapping("/v_modifyPassword.do")
	public String v_modifyPassword(HttpServletRequest request, ModelMap modelMap) {
		return "jsuser/modifyPassword";
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("/validPassword.do")
	@ResponseBody
	public ValidForm validPassword(ValidForm v) {
		// 得到当前session中的user
		String oldPassword=ObjectUtils.getParameter("param");
		
		JSUser jsUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		jsUser=mbsCommonService.selectOne("JSUser.find",jsUser.getId());
		
		if (ObjectUtils.isEmpty(oldPassword)) {
			v.setInfo("旧密码不能为空！");
			v.setStatus("n");
		
		}else	if (!jsUser.getPassWord().equals(PasswordUtil.encrypt(oldPassword, PasswordUtil.Salt, PasswordUtil.getStaticSalt()))) {
			v.setInfo( "旧密码输入不正确！");
			v.setStatus("n");
		}else{
			
				v.setInfo("密码验证成功！");
				v.setStatus("y");
		}
		return v;
		
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("/modifyPassword.do")
	@ResponseBody
	public AjaxJson modifyPassword(AjaxJson j) {
		// 得到当前session中的user
		String newPassword=ObjectUtils.getParameter("newPassword");
		JSUser jsUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		jsUser=mbsCommonService.selectOne("JSUser.find",jsUser.getId());
		if (ObjectUtils.isEmpty(newPassword)) {
			j.setInfo("新密码不能为空！","n");
		
		}else{
			jsUser.setPassWord(PasswordUtil.encrypt(newPassword, PasswordUtil.Salt, PasswordUtil.getStaticSalt()));
		
			j= mbsCommonService.updatePassword(jsUser);
			if(j.isSuccess()){
				j.setInfo("密码修改成功！","y");
				
			}else{
				j.setInfo("密码修改失败！","y");
			}
		}
	
		j.setHref("v_modifyPassword.do");
		return j;
		
	}
	/**
	 * 用户修改页面跳转
	 */
	@RequestMapping("/edit.do")
	public String edit(String id, Model model,FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.order("menuCode",Sort.asc);
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList",pagerInfo);
		JSUser user = mbsCommonService.selectOne("JSUser.find", id);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		return "jsuser/edit";
	}
	/**
	 * 用户修改页面跳转
	 */
	@RequestMapping("/editgx.do")
	public String editgx(String id, String oid,Model model,FilterHandler filterHandler) {
		JSUser user = mbsCommonService.selectOne("JSUser.find", id);
		model.addAttribute("user", user);
		model.addAttribute("oid", oid);
		return "jsuser/editgx";
	}
	/**
	 * 用户修改页面跳转
	 */
	@RequestMapping("/bedit.do")
	public String bedit(String id, String orgid,Model model,FilterHandler filterHandler) {
		JSUser user = mbsCommonService.selectOne("JSUser.find", id);
		model.addAttribute("user", user);
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSOrganization jsorg = mbsCommonService.findOneOrganByUser(user.getId());
		if(!ObjectUtils.isEmpty(jsorg)){
			model.addAttribute("orgid",jsorg.getId());
		}
		//model.addAttribute("orgid", orgid);
		List<JSOrganization> orgList = mbsCommonService.findOrganByUserIsAdmin();
		if(mbsCommonService.getUserType(jUser)==2){
			orgList.add(jsorg);
		}
		model.addAttribute("orgList", orgList);
		return "jsuser/bedit";
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxJson save(AjaxJson j, JSUser jsUser, String[] organizationIds, String[] roleIds, String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setPassWord(PasswordUtil.encrypt("123456",PasswordUtil.Salt, PasswordUtil.getStaticSalt()));
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_1);
		jsUser.setIsLock((short) 1);
		j = mbsCommonService.insert("JSUser.save", jsUser);
		if (j.isSuccess()) {
			// 分配机构
			mbsCommonService.allotOrganToUser(jsUser, organizationIds);
			// 分配角色
			mbsCommonService.allotRoleToUser(jsUser, roleIds);
		}
		return j;
	}
	/**
	 * 保存信息和工业用户
	 */
	@RequestMapping("/savegx.do")
	@ResponseBody
	public AjaxJson savegx(AjaxJson j, JSUser jsUser, String oid, String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setPassWord(PasswordUtil.encrypt("123456",PasswordUtil.Salt, PasswordUtil.getStaticSalt()));
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_1);
		jsUser.setIsLock((short) 1);
		j = mbsCommonService.insert("JSUser.save", jsUser);
		if (j.isSuccess()) {
			// 分配机构
			mbsCommonService.allotOrganToUser(jsUser,new String[]{oid});
			// 分配角色
			mbsCommonService.allotRoleToUser(jsUser, new String[]{"7"});
		}
		if(!StringUtils.isEmpty(oid)&&"2".equals(oid)){
			j.setHref("xlist.do");
		}else{
			j.setHref("glist.do");
		}
		return j;
	}
	/**
	 * 保存信息和工业用户
	 */
	@RequestMapping("/bsave.do")
	@ResponseBody
	public AjaxJson bsave(AjaxJson j, JSUser jsUser, String orgid, String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setPassWord(PasswordUtil.encrypt("123456",PasswordUtil.Salt, PasswordUtil.getStaticSalt()));
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_2);
		jsUser.setIsLock((short) 1);
		j = mbsCommonService.insert("JSUser.save", jsUser);
		if (j.isSuccess()) {
			// 分配机构
			mbsCommonService.allotOrganToUser(jsUser,new String[]{orgid});
			// 分配角色
			mbsCommonService.allotRoleToUser(jsUser, new String[]{"7"});
		}
		j.setHref("busermain.do");
		return j;
	}
	/**
	 * 保存权限
	 */
	@RequestMapping("/savePermission.do")
	@ResponseBody
	public AjaxJson savePermission(AjaxJson j, String permissions, String userId) {
		mbsCommonService.savePermission(permissions, userId, Global.JSUser);
		j.setHref("list.do");
		j.setInfo("权限分配成功");
		return j;
	}
	/**
	 * 更新用户
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	@Transaction
	public AjaxJson update(AjaxJson j, JSUser jsUser, String[] organizationIds, String[] roleIds, String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_1);
		j= mbsCommonService.update("JSUser.update", jsUser);
		if(j.isSuccess())
		{
		// 分配机构
		mbsCommonService.allotOrganToUser(jsUser, organizationIds);
		
		// 分配角色
		mbsCommonService.allotRoleToUser(jsUser, roleIds);
		}
		return j;
	}
	/**
	 * 更新用户
	 */
	@RequestMapping("/updategx.do")
	@ResponseBody
	@Transaction
	public AjaxJson updategx(AjaxJson j, JSUser jsUser, String oid,String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_1);
		j= mbsCommonService.update("JSUser.update", jsUser);
		if(!StringUtils.isEmpty(oid)&&"2".equals(oid)){
			j.setHref("xlist.do");
		}else{
			j.setHref("glist.do");
		}
		return j;
	}

	/**
	 * 更新用户
	 */
	@RequestMapping("/bupdate.do")
	@ResponseBody
	@Transaction
	public AjaxJson bupdate(AjaxJson j, JSUser jsUser, String orgid,String permissions) {
		jsUser.setUserType(Global.JSUser);
		jsUser.setNickName(jsUser.getRealName());
		jsUser.setUserLevel(Global.JSUser_2);
		j= mbsCommonService.update("JSUser.update", jsUser);
		j.setHref("busermain.do");
		return j;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public AjaxJson delete(AjaxJson j, String[] ids) {
		return mbsCommonService.deleteUser(getIds(ids));
	}
	/**
	 * 删除信息用户
	 */
	@RequestMapping("deletexy.do")
	@ResponseBody
	public AjaxJson deletex(AjaxJson j, String oid,String[] ids) {
		j=mbsCommonService.deleteUser(getIds(ids));
		if(!StringUtils.isEmpty(oid)&&"2".equals(oid)){
			j.setHref("xlist.do");
		}else if(!StringUtils.isEmpty(oid)&&"6".equals(oid)){
			j.setHref("busermain.do");
		}else{
			j.setHref("glist.do");
		}
		return j;
	}
	/**
	 * 删除办事处用户
	 */
	@RequestMapping("deletearea.do")
	@ResponseBody
	public AjaxJson deletearea(AjaxJson j, String[] ids) {
		j= mbsCommonService.deleteUser(getIds(ids));
		j.setHref("arealist.do");
		return j;
	}


	/**
	 * 分配权限页面跳转
	 */
	@RequestMapping("/permission.do")
	public String list(String ownerId, Model model, ModelMap modelMap,String otype,FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setIsPage(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.order("menuCode", Sort.asc);
		if(!StringUtils.isEmpty(otype)){
			cq.cn("otype", otype);
			model.addAttribute("otype", otype);
		}
		List<JSMenu> list = mbsCommonService.selectList("JSMenu.pagerList", pagerInfo);
		JSUser user = mbsCommonService.selectOne("JSUser.find", ownerId);
		model.addAttribute("list", list);
		model.addAttribute("user", user);
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		model.addAttribute("imgPath", imgPath);
		return "jsuser/permission";
	}

	/**
	 * 高级查询页面跳转
	 */
	@RequestMapping("search.do")
	public String search() {
		return "jsuser/search";
	}
	/**
	 * 选择人员
	 */
	@RequestMapping("selectuser.do")
	public String selectuser() {
		return "jsuser/selectuser";
	}

	/**
	 * 用户选择器页面
	 */
	@RequestMapping("select.do")
	public String select(JSUser jsUser, FilterHandler filterHandler, Model model) {
		Short level = ConvertUtils.getShort(ObjectUtils.getParameter("level"));
		//List<JSUser> list = mbsCommonService.findUserByLevel(level,jsUser,filterHandler);
		level=null;
		List<JSUser> list = mbsCommonService.selectList("JSUser.findAll");
		model.addAttribute("list", list);
		return "jsuser/select";
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
	 * 公司添加坐标
	 */
	@RequestMapping("/addPoint.do")
	public String addPoint(String id,String point,Model model){
		JSUser user=new JSUser();
		user.setId(id);
		if(!StringUtils.isEmpty(point)){
			String xy[]=point.split(",");
			user.setX(Double.valueOf(xy[0]));
			user.setY(Double.valueOf(xy[1]));
			mbsCommonService.update("JSUser.update", user);
		}
		user=mbsCommonService.selectOne("JSUser.findById", id);
		model.addAttribute("user", user);
		//return "jsuser/addPointMap";
		return "jsuser/addSogouPointMap";
	}
	
	/**
	 * 验证用户名的重复性
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/validateName.do")
	public void validateName(String userName,HttpServletResponse respone){
		respone.setCharacterEncoding("UTF-8");
		Map map = new HashMap<String, String>();
		map.put("userName", userName);
		JSUser user = mbsCommonService.selectOne("JSUser.findByUserName", map);
		String result = "";
		if(user == null)
			result = "false";
		else
			result =  "true";
	    PrintWriter out;
		try {
			out = respone.getWriter();
			out.write(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
