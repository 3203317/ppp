package com.qingshu.common.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qingshu.base.pojo.JSMenu;
import com.qingshu.base.pojo.JSOperation;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSPermission;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.constants.Global;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.DirectiveUtil;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.ResourceUtil;
import com.qingshu.core.mybatis.service.MbsCommonService;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * freemarker自定义标签方法处理类
 */
public class CommonDirective {
	protected MbsCommonService mbsCommonService;

	public CommonDirective(MbsCommonService mbsCommonService) {
		this.mbsCommonService = mbsCommonService;
	}

	/**
	 * 角色菜单操作权限分配
	 */
	public Map<String, TemplateModel> getPermission(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		String menuId = DirectiveUtil.getString("menuId", paramWrap);/* 菜单ID */
		String ownerId = DirectiveUtil.getString("ownerId", paramWrap);/* 权限拥有者ID */
		String menuPid = DirectiveUtil.getString("menuPid", paramWrap);/* 菜单父ID */
		String ownerType = DirectiveUtil.getString("ownerType", paramWrap);/* 权限拥有者类型 */
		Map<String, Object> operationMap = ObjectUtils.getHashMap();
		operationMap.put("permissionId", menuId);
		operationMap.put("permissionType", Global.JSMenu);
		StringBuffer sb = new StringBuffer();
		List<JSOperation> list = mbsCommonService.selectList("JSOperation.selectMenuOperation", operationMap);/* 查询该菜单拥有的操作 */
		List<JSOperation> ownerOperations = new ArrayList<JSOperation>();/* 角色在该权限上已经分配的操作 */
		if (!ObjectUtils.isEmpty(ownerId)) {

			Map<String, Object> permissionMap = ObjectUtils.getHashMap();
			permissionMap.put("permissionType", Global.JSMenu);
			permissionMap.put("ownerType", ownerType);
			permissionMap.put("permissionId", menuId);
			permissionMap.put("ownerId", ownerId);

			JSPermission jsPermission = mbsCommonService.selectOne("JSPermission.selectByMap", permissionMap);/* 权限对象 */
			if (jsPermission != null) {
				ownerOperations = jsPermission.getOperationList();
			}
		}
		for (JSOperation jsOperation : list) {
			Boolean check = false;
			if (ownerOperations.size() > 0) {
				for (JSOperation ownerOperation : ownerOperations) {
					if (ownerOperation.getId().equals(jsOperation.getId())) {
						check = true;
					}
				}
			}
			sb.append("<input id=\"check_" + menuPid + "_" + menuId + "_" + jsOperation.getId() + "\" type=\"checkbox\" name=\"operation\"");
			if (check) {
				sb.append(" checked=\"checked\"");
			}
			sb.append(" value=\"" + jsOperation.getId() + "\" onclick=\"Fm.checkParent(this.id);\">" + jsOperation.getOperationName() + " ");
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.toString()));
		return paramWrap;
	}

	/**
	 * 检查角色菜单权限是否已分配
	 */
	public Map<String, TemplateModel> checkPermission(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		String menuId = DirectiveUtil.getString("menuId", paramWrap);/* 菜单ID */
		String ownerId = DirectiveUtil.getString("ownerId", paramWrap);/* 权限拥有者ID */
		String ownerType = DirectiveUtil.getString("ownerType", paramWrap);/* 权限拥有者类型 */
		Map<String, Object> permissionMap = ObjectUtils.getHashMap();
		permissionMap.put("permissionType", Global.JSMenu);
		permissionMap.put("ownerType",ownerType);
		permissionMap.put("permissionId", menuId);
		permissionMap.put("ownerId", ownerId);
		JSPermission jsPermission = mbsCommonService.selectOne("JSPermission.selectByMap", permissionMap);/* 权限对象 */
		StringBuffer sb = new StringBuffer();
		if (jsPermission != null) {
			sb.append(" checked=\"checked\"");
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.toString()));
		return paramWrap;
	}

	/**
	 * 初始化角色左侧菜单
	 */
	public Map<String, TemplateModel> initLeftMenu(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		// String userId = DirectiveUtil.getString("userId", paramWrap);/* 用户ID */
		String imgPath = ResourceUtil.getConfigByName("Icon_Small_BasePath");
		Map<String, Object> map = ObjectUtils.getHashMap();
		JSUser jUser=SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		List<JSRole> roles=mbsCommonService.findRoleByUser(jUser.getId());
		List<String> roList=ObjectUtils.getArrayList();
		for (JSRole jsRole : roles) {
			roList.add(jsRole.getId());
		}
		map.put("ownerList", ConvertUtils.listToArray(roList));
		map.put("ownerType", Global.JSRole);
		map.put("permissionType", Global.JSMenu);
		//登录人信息
		map.put("currentUserId", jUser.getId());
		map.put("currentUserType", Global.JSUser);
		List<JSMenu> jsMenus = mbsCommonService.selectList("JSMenu.findJSMenuByRoleId", map);
		List<JSMenu> firstMenu = getJSMenu(jsMenus, Global.FirstMenu);
		List<JSMenu> secondMenu = getJSMenu(jsMenus, Global.SecondMenu);
		StringBuffer sb = new StringBuffer();
		if (firstMenu.size() > 0) {
			for (JSMenu jsMenu : firstMenu) {
				sb.append("<div class=\"menu_title\"> " + "<img src=\"../" + imgPath+"/" + jsMenu.getMenuImg()+ "\" style=\"height:18px;width:18px;\"/> " + jsMenu.getMenuName() + "</div>");
				if (secondMenu.size() > 0) {
					sb.append("<ul class=\"menu_body\">");
					for (int i = 0; i < secondMenu.size(); i++) {
						if(secondMenu.get(i).getMenuPid()==null)
						{
							System.out.print(secondMenu.get(i).getMenuName());
						}
						if (secondMenu.get(i).getMenuPid()!=null&&jsMenu.getId().equals(secondMenu.get(i).getMenuPid().getId())) {
							sb.append("<li onclick=\"NavMenu(\'" + secondMenu.get(i).getMenuUrl() + "\',\'" + secondMenu.get(i).getMenuName() + "\',\'" + jsMenu.getMenuName() + "\')\"> " 
									+ "<img src=\"../" + imgPath+"/" + secondMenu.get(i).getMenuImg()+ "\" style=\"height:18px;width:18px;\"/> "
							+ secondMenu.get(i).getMenuName() + " </li>");
						}
					}
					sb.append("</ul>");
				}
			}
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.toString()));
		return paramWrap;
	}
	/**
	 * 根据id获得用户名
	 */
	public Map<String, TemplateModel> getUsername(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		// String userId = DirectiveUtil.getString("userId", paramWrap);/* 用户ID */
		Map<String, Object> map = ObjectUtils.getHashMap();
		String id=paramWrap.get("userid").toString();
		String[] ids=id.split(",");
		List<JSUser> list=mbsCommonService.selectList("JSUser.findByIds",ids);
		StringBuffer sb = new StringBuffer();
		if(list!=null&&list.size()>0){
			for(JSUser user:list)
			sb.append(user.getNickName()+",");
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.substring(0, sb.length()-1).toString()));
		return paramWrap;
	}
	/**
	 * 根据用户id获得机构名称
	 */
	public Map<String, TemplateModel> getOrganizaName(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		String id=paramWrap.get("userId").toString();
		List<JSOrganization> list=mbsCommonService.findOrganByUser(id);
		StringBuffer sb = new StringBuffer();
		if(list!=null&&list.size()>0){
			for(JSOrganization organization:list)
			sb.append(organization.getOrganizationName()+",");
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.substring(0, sb.length()-1).toString()));
		return paramWrap;
	}
	/**
	 * 根据机构id获得机构名称
	 */
	public Map<String, TemplateModel> findOrgName(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		String id=paramWrap.get("orgid").toString();
		JSOrganization org=mbsCommonService.findOrganById(id);
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(org.getOrganizationName()));
		return paramWrap;
	}
	
	/**
	 * 根据用户id获得角色名称
	 */
	public Map<String, TemplateModel> getRoleName(Map<String, TemplateModel> paramWrap) throws TemplateModelException {
		String id=paramWrap.get("userId").toString();
		List<JSRole> list=mbsCommonService.findRoleByUser(id);
		StringBuffer sb = new StringBuffer();
		if(list!=null&&list.size()>0){
			for(JSRole role:list)
			sb.append(role.getRoleName()+",");
		}
		paramWrap.put(DirectiveUtil.OUT_BEAN, DEFAULT_WRAPPER.wrap(sb.substring(0, sb.length()-1).toString()));
		return paramWrap;
	}
	
	/**
	 * 获取分级菜单
	 */
	private List<JSMenu> getJSMenu(List<JSMenu> jsMenus, Short level) {
		List<JSMenu> list = new ArrayList<JSMenu>();
		for (JSMenu jsMenu : jsMenus) {
			if (level.equals(jsMenu.getMenuLevel())) {
				list.add(jsMenu);
			}
		}
		return list;
	}

}
