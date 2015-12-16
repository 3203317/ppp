package com.qingshu.common.util;

import java.io.Serializable;


/**
 * 用户Session对象
 * 
 * @param <T>
 * @param <T>
 */
public class SessionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String USER_SESSION = "user_session";//前台用户session
	public static String ADMIN_SESSION = "admin_session";//后台用户session
	public static String AUTHS="auths";//权限表所有权限
	public static String OPT_AUTHS="optauths";//机构操作权限列表
	public static String SLAVEID_SET="slaveidset";//机构能操作的从机构id集合
	public static String DATA_BACKUP="backup";//数据备份文件夹

	/**
	 * 设置SessionInfo
	 * 
	 * @param sessionInfo
	 */
	public static void setSessionInfo(Object object,String sessionType) {
		ContextUtils.getSession().setAttribute(sessionType, object);
	}

	/**
	 * 获取SessionInfo
	 */

	@SuppressWarnings("unchecked")
	public static <T> T getSessionInfo(String sessionType) {
		return (T) ContextUtils.getSession().getAttribute(sessionType);
	}
}
