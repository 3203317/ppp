package com.qingshu.core.mybatis.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.qingshu.base.pojo.JSArea;
import com.qingshu.base.pojo.JSDictionary;
import com.qingshu.base.pojo.JSLog;
import com.qingshu.base.pojo.JSMessage;
import com.qingshu.base.pojo.JSOrganization;
import com.qingshu.base.pojo.JSPermission;
import com.qingshu.base.pojo.JSRole;
import com.qingshu.base.pojo.JSUser;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.json.model.AjaxJson;
import com.qingshu.common.json.model.Upload;
import com.qingshu.common.plugin.poi.PoiModel;
import com.qingshu.common.plugin.upload.UploadFile;

/**
 * 公共方法接口
 * 
 * @author zyf
 * @param <T>
 */
public interface MbsCommonService extends MbsGenericService {
	/**
	 * 查询用户所在机构
	 */
	public List<JSOrganization> findOrganByUser(String userId);
	/**
	 * 查询当前登录用户所在机构
	 */
	public List<JSOrganization> findOrganByUser();
	/**
	 * 当用户是管理员时获取所有办事处
	 */
	public List<JSOrganization> findOrganByUserIsAdmin();
	/**
	 * 查询当前登录用户所在机构
	 */
	public JSOrganization findOneOrganByUser();
	/**
	 * 根据机构ID查询机构编码
	 */
	public String findAreaCode(String organizationId);

	/**
	 * 查询用户所在机构
	 */
	public JSOrganization findOneOrganByUser(String userId);
	/**
	 * 查询用户所在机构
	 */
	public JSOrganization findOrganByOrgcode(String orgcode);
	/**
	 * 更新用户锁定状态
	 */
	public AjaxJson updateLock(JSUser jUser);
	/**
	 * 修改用户密码
	 */
	public AjaxJson updatePassword(JSUser jUser);
	/**
	 * 查询机构下用户
	 */
	public List<JSUser> findUserByOrgan(String organId);
	/**
	 * 根据机构ID和用户类型获取用户
	 */
	public List<JSUser> findUserByOrganAndUserType(String organId,String userType) ;

	/**
	 * 查询角色下用户
	 */
	public List<JSUser> findUserByRole(String roleId);

	/**
	 * 根据角色编码获取角色
	 */
	public JSRole findRoleByCode(String roleCode);

	/**
	 * 查询用户下角色
	 */
	public List<JSRole> findRoleByUser(String userId);

	/**
	 * 查询机构
	 */
	public JSOrganization findOrganById(String organId);

	/**
	 * 删除用户角色
	 */
	public AjaxJson deleteUserRole(String userId);

	/**
	 * 删除用户机构
	 */
	public AjaxJson deleteUserOgran(String userId);
	/**
	 * 删除用户机构
	 */
	public AjaxJson deleteAllotUser(AjaxJson j,String roleId, String[] userId);

	/**
	 * 删除用户
	 */
	public AjaxJson deleteUser(String[] userId);

	/**
	 * 查询角色拥有的权限
	 */
	public List<JSPermission> findPermissionByRole(String roleId);

	/**
	 * 删除用户拥有的权限
	 */
	public void deletePermission(String ownerId,String ownerType);

	/**
	 * 保存权限
	 */
	public void savePermission(String permissions, String userId,String ownerType);

	/**
	 * 分配机构给用户
	 */
	public void allotOrganToUser(JSUser jsUser, String[] organizationIds);

	/**
	 * 分配角色
	 */
	public void allotRoleToUser(JSUser jsUser, String[] roleIds);

	/**
	 * 删除用户
	 */
	public AjaxJson deleteUser(String userId);
	public List<JSOrganization> findOrganByLevel(Short level);

	/**
	 * 根据用户等级查询用户
	 */
	public List<JSUser> findUserByLevel(Short level, JSUser jsUser, FilterHandler filterHandler);
	/**
	 * 根据行政区域等级查询行政区域
	 */
	public List<JSArea> findJSAreaByLevel(String level);
	/**
	 * 根据权限ID和权限类型删除已分配的权限
	 */
	public AjaxJson deleteMenuPermission(String permissionId, String permissionType);

	/**
	 * 根据机构级别获取结构
	 */
	public List<JSOrganization> findOrganizationByLevel(Short level);
	/**
	 * 根据字段分组类型查找该类型的所有分类
	 */
	public List<JSDictionary> findDictionaryByCode(String dictGroupCode);

	/**
	 * 文件导入
	 */
	public PoiModel importFile(PoiModel poiModel);
	/**
	 * 文件下载或预览
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);
	/**
	 * 文件上传
	 */
	public Upload upload(UploadFile uploadFile);
	/**
	 * 获取用户类型
	 */
	public Integer getUserType(JSUser user);
	/**
	 * 获取用户信息还是经济型
	 */
	public Integer getUserGorX(JSUser user);
	/**
	 * 日志插入(系统切面自动插入)
	 */
	public AjaxJson insertLog(JSLog jsLog);
	/**
	 * 日志插入(手工插入)
	 */
	public AjaxJson insertLog(String logDesc,String logEntity,String methodName);
	/**
	 * 发送消息
	 */
	public AjaxJson sendMessage(JSMessage jsMessage);

}
