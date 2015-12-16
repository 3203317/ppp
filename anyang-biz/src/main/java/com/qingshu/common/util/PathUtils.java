package com.qingshu.common.util;

import com.qingshu.base.pojo.JSRole;

/**
 * @description：(各种路径获取)
 * @author：flyme
 * @data：2013-8-26 下午02:08:02
 * @version：v 1.0
 */
public class PathUtils {
	/**
	 * 获取项目绝对路径 例：C:\Tomcat6\webapps\qingshu\
	 */
	public static String getRealPath() {
		return ContextUtils.getSession().getServletContext().getRealPath("/");
	}

	public static String getRealPath(String path) {
		FileUtils.makDirs(ContextUtils.getSession().getServletContext().getRealPath("/")+path);
		return ContextUtils.getSession().getServletContext().getRealPath(path)+"/";
	}

	/**
	 * 获取请求全路径 例：/qingshu/admin/check.do
	 */
	public static String getRequestURI() {
		return ContextUtils.getRequest().getRequestURI();

	}

	/**
	 * 获取工程名 例：/qingshu
	 */
	public static String getContextPath() {
		return ContextUtils.getRequest().getContextPath();
	}

	/**
	 * 获取当前页面所在目录下全名称 例：/qingshu
	 */
	public static String getServletPath() {
		return ContextUtils.getRequest().getServletPath();

	}

	/**
	 * 获取user.dir
	 */
	public static String getProperty() {
		return System.getProperty("user.dir");

	}

	public static void main(String[] args) {
		JSRole jRole=new JSRole();
		JSRole jRole2=new JSRole();
		JSRole jRole3=new JSRole();
		jRole2.setRolePid(jRole3);
		jRole.setRolePid(jRole2);
		
		ReflectHelper reflectHelper=new ReflectHelper(jRole);
		Object aObject=reflectHelper.setMethodValue("rolePid_rolePid_roleName","cc");
		System.out.println(aObject.toString());
	}
}
