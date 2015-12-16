package com.qingshu.core.mybatis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshu.base.pojo.JSArea;
import com.qingshu.base.pojo.JSDictionary;
import com.qingshu.base.pojo.JSDictionaryGroup;
import com.qingshu.base.pojo.JSLog;
import com.qingshu.base.pojo.JSMessage;
import com.qingshu.base.pojo.JSMessageReceiver;
import com.qingshu.base.pojo.JSMessageSend;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSPermission;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.base.vo.PermissionOperation;
import com.qingshu.base.vo.UserOrganization;
import com.qingshu.base.vo.UserRole;
import com.qingshu.common.constants.Global;
import com.qingshu.common.filters.CriteriaQuery;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.GroupOp;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.Operation;
import com.qingshu.common.json.model.Permission;
import com.qingshu.common.json.model.SessionInfo;
import com.qingshu.common.json.model.Upload;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.plugin.poi.PoiModel;
import com.qingshu.common.plugin.upload.UploadFile;
import com.qingshu.common.util.DateUtils;
import com.qingshu.common.util.JsonUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.core.mybatis.service.MbsCommonService;

@Service("mbsCommonService")
@Transactional
public class MbsCommonServiceImpl extends MbsGenericServiceImpl implements MbsCommonService {

	public List<JSOrganization> findOrganByUser(String userId) {
		return mbsCommonDao.selectList("JSOrganization.findOrganByUser", userId);
	}

	public List<JSOrganization> findOrganByUser() {
		JSUser jsuser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		return mbsCommonDao.selectList("JSOrganization.findOrganByUser", jsuser.getId());
	}

	public JSOrganization findOneOrganByUser(String userId) {
		List<JSOrganization> jsOrganizations = findOrganByUser(userId);
		if (jsOrganizations.size() > 0) {
			return jsOrganizations.get(0);
		} else {
			return null;
		}
	}

	public JSOrganization findOneOrganByUser() {
		JSUser jsuser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		List<JSOrganization> jsOrganizations = findOrganByUser(jsuser.getId());
		if (jsOrganizations.size() > 0) {
			return jsOrganizations.get(0);
		} else {
			return null;
		}
	}
	public String findAreaCode(String organizationId)
	{
		return mbsCommonDao.selectOne("JSArea.findAreaCode",organizationId);
	}
	public List<JSOrganization> findOrganByUserIsAdmin() {
		JSUser jsuser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		List<JSOrganization> organizations = ObjectUtils.getArrayList();
		if (getUserType(jsuser).equals(1)) {
			organizations = findOrganizationByLevel(Global.JSOrgain_2);
		}
		return organizations;
	}

	public List<JSUser> findUserByOrgan(String organId) {
		return mbsCommonDao.selectList("JSOrganization.findUserByOrgan", organId);

	}

	public List<JSUser> findUserByOrganAndUserType(String organId, String userType) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("organId", organId);
		map.put("userType", userType);
		return mbsCommonDao.selectList("JSOrganization.findUserByOrganAndUserType", map);

	}

	public JSOrganization findOrganById(String organId) {
		return mbsCommonDao.selectOne("JSOrganization.findById", organId);

	}

	public AjaxJson updateLock(JSUser jUser) {
		return mbsCommonDao.update("JSUser.updateLock", jUser);
	}
	public AjaxJson updatePassword(JSUser jUser)
	{
		return mbsCommonDao.update("JSUser.updatePassword", jUser);
	}
	public List<JSUser> findUserByRole(String roleId) {
		return mbsCommonDao.selectList("JSUser.findUserByRole", roleId);
	}

	public List<JSRole> findRoleByUser(String userId) {
		return mbsCommonDao.selectList("JSRole.findRoleByUser", userId);
	}

	public AjaxJson deleteUserRole(String userId) {
		return mbsCommonDao.delete("JSRole.deleteUserRole", userId);
	}

	public AjaxJson deleteUserOgran(String userId) {
		return mbsCommonDao.delete("JSOrganization.deleteUserOgran", userId);
	}

	public List<JSPermission> findPermissionByRole(String roleId) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("ownerId", roleId);
		map.put("permissionType", Global.JSMenu);
		map.put("ownerType", Global.JSRole);
		List<JSPermission> list = mbsCommonDao.selectList("JSPermission.selectByMap", map);
		return list;
	}

	public void deletePermission(String ownerId, String ownerType) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("ownerId", ownerId);
		map.put("permissionType", Global.JSMenu);
		map.put("ownerType", ownerType);
		List<JSPermission> list = mbsCommonDao.selectList("JSPermission.selectByMap", map);
		if (list.size() > 0) {
			for (JSPermission jsPermission : list) {
				mbsCommonDao.delete("JSPermission.deleteOperation", jsPermission.getId());/* 删除菜单权限拥有的操作 */
				mbsCommonDao.delete("JSPermission.delete", jsPermission.getId());/* 删除菜单权限 */
			}
		}
	}

	public void savePermission(String permissions, String ownerId, String ownerType) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("operations", Operation.class);
		List<Permission> list = JsonUtils.toList(permissions, Permission.class, map);
		List<JSPermission> permissionsList = new ArrayList<JSPermission>();
		this.deletePermission(ownerId, ownerType);
		if (list.size() > 0) {
			for (Permission permission : list) {
				JSPermission jPermission = new JSPermission();
				jPermission.setOwnerType(ownerType);
				jPermission.setOwnerId(ownerId);
				jPermission.setPermissionId(permission.getMenuId());
				jPermission.setPermissionType(Global.JSMenu);
				permissionsList.add(jPermission);
				AjaxJson j = mbsCommonDao.insert("JSPermission.save", jPermission);/* 保存菜单权限 */
				if (jPermission != null) {
					List<Operation> operations = permission.getOperations();
					if (operations.size() > 0) {
						List<PermissionOperation> poList = new ArrayList<PermissionOperation>();
						for (Operation operation : operations) {
							PermissionOperation po = new PermissionOperation();
							po.setOperationId(operation.getOperationId());
							po.setPermissionId(j.getAttributes().get("id").toString());
							poList.add(po);
						}
						mbsCommonDao.batchInsert("JSPermission.batchInsertOperation", poList);/* 保存操作权限 */
					}
				}
			}
		}
	}

	public void allotOrganToUser(JSUser jsUser, String[] organizationIds) {
		this.deleteUserOgran(jsUser.getId());
		/* 清空用户机构 */
		if (!ObjectUtils.isEmpty(organizationIds)) {
			List<UserOrganization> list = ObjectUtils.getArrayList();
			for (String id : organizationIds) {
				UserOrganization userOrganization = new UserOrganization();
				userOrganization.setOrganizationId(id);
				userOrganization.setUserId(jsUser.getId());
				list.add(userOrganization);
			}
			mbsCommonDao.batchInsert("JSOrganization.batchAllotUser", list);
		}
	}

	/**
	 * 分配角色
	 */
	public void allotRoleToUser(JSUser jsUser, String[] roleIds) {
		this.deleteUserRole(jsUser.getId());
		if (!ObjectUtils.isEmpty(roleIds)) {
			List<UserRole> list = ObjectUtils.getArrayList();
			for (String id : roleIds) {
				if(id!=null&&!"".equals(id)){
					UserRole userRole = new UserRole();
					userRole.setRoleId(id);
					userRole.setUserId(jsUser.getId());
					list.add(userRole);
				}
			}
			mbsCommonDao.batchInsert("JSRole.batchAllotUser", list);
		}
	}

	/**
	 * 根据角色编码获取角色
	 */
	public JSRole findRoleByCode(String roleCode) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("roleCode", roleCode);
		JSRole jsRole = mbsCommonDao.selectOne("JSRole.findByCode", map);
		return jsRole;
	}

	/**
	 * 根据权限ID和权限类型删除已分配的权限
	 */
	public AjaxJson deleteMenuPermission(String permissionId, String permissionType) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("permissionId", permissionId);
		map.put("permissionType", permissionType);
		return mbsCommonDao.delete("JSPermission.deleteMenuPermission", map);
	}

	/**
	 * 删除角色已分配的用户
	 */
	public AjaxJson deleteAllotUser(AjaxJson j, String roleId, String[] userId) {
		Map<String, Object> params = ObjectUtils.getHashMap();
		params.put("id", roleId);
		params.put("ids", userId);
		j = mbsCommonDao.delete("JSRole.deleteAllotUser", params);
		return j;
	}

	/**
	 * 删除用户
	 */
	@Transactional
	public AjaxJson deleteUser(String[] userIds) {
		AjaxJson j = new AjaxJson();
		for (String userId : userIds) {
			j = deleteUser(userId);
		}
		return j;
	}

	/**
	 * 删除用户
	 */
	public AjaxJson deleteUser(String userIds) {
		AjaxJson j = new AjaxJson();
		this.deletePermission(userIds, Global.JSUser);/* 清空用户权限 */
		this.deleteUserOgran(userIds);/* 删除用户机构 */
		this.deleteUserRole(userIds);/* 删除用户角色 */
		j = mbsCommonDao.delete("JSUser.delete", userIds);
		return j;
	}

	public List<JSUser> findUserByLevel(Short level, JSUser jsUser, FilterHandler filterHandler) {
		PagerInfo pagerInfo = new PagerInfo(filterHandler);
		pagerInfo.setSimplePager(false);
		CriteriaQuery cq = new CriteriaQuery(pagerInfo, GroupOp.and);
		cq.eq("userName", jsUser.getUserName());
		cq.eq("userLevel", level);
		return mbsCommonDao.selectList("JSUser.pagerList", pagerInfo);
	}

	public List<JSOrganization> findOrganizationByLevel(Short level) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("organizationLevel", level);
		return mbsCommonDao.selectList("JSOrganization.findOrganizationByLevel", map);
	}

	public List<JSOrganization> findOrganByLevel(Short level) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("organizationLevel", level);
		return mbsCommonDao.selectList("JSOrganization.findOrganizationByLevel", map);
	}
	public List<JSArea> findJSAreaByLevel(String level) {
		FilterHandler filterHandler=new FilterHandler();
		CriteriaQuery cq = new CriteriaQuery(filterHandler, GroupOp.and);
		cq.getPagerInfo().setIsPage(false);
		cq.in("jsa.areaLevel", level);
		return mbsCommonDao.selectList("JSArea.pagerList",cq.getPagerInfo());
	}

	/**
	 * 根据字段分组类型查找该类型的所有分类
	 */
	public List<JSDictionary> findDictionaryByCode(String dictGroupCode) {
		List<JSDictionary> dictionaries = ObjectUtils.getArrayList();
		JSDictionaryGroup dictionaryGroup = mbsCommonDao.selectOne("JSDictionaryGroup.findByCode", dictGroupCode);
		if (!ObjectUtils.isEmpty(dictionaryGroup)) {
			dictionaries = mbsCommonDao.selectList("JSDictionary.findByGroupId", dictionaryGroup.getId());
		}
		return dictionaries;
	}
	/**
	 * 返回用户类型，1工信局用户(包括admin) 2办事处用户 3工业企业用户 4信息企业用户5工信企业用户
	 */
	public Integer getUserType(JSUser user) {
		JSOrganization organization = findOneOrganByUser(user.getId());
		Short oLevel = -1;
		if ("JSUser".equals(user.getUserType())) {
			if (ObjectUtils.isEmpty(organization)) {

			} else {
				oLevel = organization.getOrganizationLevel();
				return oLevel.intValue();
			}
		}
		 return user.getUserLevel().intValue();
		
	}
	/**
	 * 判断用户属于信息还是经济(如果用户所在部门为经济运行科或者用户拥有经济运行科这个角色，返回1；如果用户所在部门为信息科或者用户拥有信息科这个角色，返回2；如果用户满足返回1和返回2的条件，返回3；其他情况返回0)
	 */
	public Integer getUserGorX(JSUser user) {
		List<JSOrganization> organizationList = findOrganByUser(user.getId());
		short flagG=0;//信息型用户
		short flagX=0;//经济型用户
		//根据用户所在机构判断
		if (organizationList!=null&&organizationList.size()>0) {
			for(JSOrganization organization:organizationList){
				if("100101".equals(organization.getOrganizationCode())){
					flagX=2;
				}
				if("100102".equals(organization.getOrganizationCode())){
					flagG=1;
				}
			}
		}
		//根据用户拥有的角色判断
		List<JSRole> roleList=mbsCommonDao.selectList("JSUser.selectJSRoles",user.getId());
		if(roleList!=null&&roleList.size()>0){
			for(JSRole r:roleList){
				if("101".equals(r.getRoleCode())){
					flagG=1;
				}
				if("103".equals(r.getRoleCode())){
					flagX=2;
				}
			}
		}
		return flagG+flagX;
	}

	public AjaxJson insertLog(JSLog jsLog) {
		AjaxJson j = mbsCommonDao.insert("JSLog.save", jsLog);
		return j;
	}

	/**
	 * 手工添加日志
	 */
	public AjaxJson insertLog(String logDesc, String logEntity, String methodName) {
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		JSLog jsLog = new JSLog();
		jsLog.setLogIp(ObjectUtils.getIpAddr());
		jsLog.setLogDate(DateUtils.getTimestamp());
		jsLog.setLogDesc(logDesc);
		jsLog.setEntityName(logEntity);
		jsLog.setMethodName(methodName);
		jsLog.setJsUser(jUser);
		jsLog.setUserName(jUser.getNickName());
		AjaxJson j = mbsCommonDao.insert("JSLog.save", jsLog);
		return j;
	}

	/**
	 * 发送个人消息
	 */
	public AjaxJson sendMessage(JSMessage jsMessage) {
		AjaxJson j = new AjaxJson();
		JSUser jUser = SessionInfo.getSessionInfo(SessionInfo.ADMIN_SESSION);
		jsMessage.setSendUser(jUser);
		jsMessage.setReceiverType(Global.JSMessage_JSUser);
		// 根据接受人id里面是否包含逗号来判断是单发还是群发
		if (jsMessage.getReceiverId() != null && jsMessage.getReceiverId().contains(",")) {
			jsMessage.setMessageType(Global.JSMessage_Public);
		} else {
			jsMessage.setMessageType(Global.JSMessage_Private);
		}
		jsMessage.setStatus(Global.JSMessage_New);
		jsMessage.setCreateTime(DateUtils.getTimestamp());
		// 添加信息表记录
		j = mbsCommonDao.insert("JSMessage.save", jsMessage);
		if (j.isSuccess()) {
			// 添加发送表记录
			JSMessageSend jsMessageSend = new JSMessageSend();
			jsMessageSend.setJsMessage((JSMessage) j.getObject());
			jsMessageSend.setSendUser(jsMessage.getSendUser());
			jsMessageSend.setSendTime(DateUtils.getTimestamp());
			jsMessageSend.setStatus(Global.JSMessage_New);
			mbsCommonDao.insert("JSMessageSend.save", jsMessageSend);
			// 添加接收表记录
			insertReceiveRecords(jsMessage);
		}
		return j;
	}

	public void insertReceiveRecords(JSMessage jsMessage) {
		String[] ids = jsMessage.getReceiverId().split(",");
		for (String id : ids) {
			JSMessageReceiver receiver = new JSMessageReceiver();
			receiver.getReceiverUser().setId(id);
			receiver.getJsMessage().setId(jsMessage.getId());
			receiver.setStatus(Global.JSMessage_New);
			receiver.setReceiverTime(DateUtils.getTimestamp());
			receiver.setStatus(Short.valueOf("0"));
			mbsCommonDao.insert("JSMessageReceiver.save", receiver);
		}
	}
	/**
	 * Excel导入
	 */
	public PoiModel importFile(PoiModel poiModel) {
		return mbsCommonDao.importFile(poiModel);
	}
	/**
	 * 文件上传
	 */
	public Upload upload(UploadFile uploadFile)
	{
		return mbsCommonDao.upload(uploadFile);
	}
	/**
	 * 文件下载或预览
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)
	{
		return mbsCommonDao.viewOrDownloadFile(uploadFile);
	}

	@Override
	public JSOrganization findOrganByOrgcode(String orgcode) {
		Map<String, Object> map = ObjectUtils.getHashMap();
		map.put("organizationCode", orgcode);
		return mbsCommonDao.selectOne("JSOrganization.findOrganCode",map);
	}
}
