package com.qingshu.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {
	
	/**
	 * 返回请求的绝对路径url
	 */
	public static String getUrl(HttpServletRequest request){
		return String.valueOf(request.getRequestURL());
	}
	
	/**
	 * 返回请求的相对路径uri
	 */
	public static String getUri(HttpServletRequest request){
		return request.getRequestURI();
	}
	
	public static String getContext(HttpServletRequest request){
		return request.getContextPath();
	}
	
	/**
	 * 获取最后的地址路径
	 */
	public static String getLastUri(HttpServletRequest request){
		String url = String.valueOf(request.getRequestURL());
		String context = request.getContextPath();
		return url.substring(context.length()-1);
	}
	public static String getString(Object object) {
		if (StringUtils.isEmpty(object)) {
			return "";
		}
		return (object.toString().trim());
	}
	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * 获取web路径
	 */
	public static String getRealPath()
	{
		return getRequest().getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 获取指定路径的绝对路径
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path){
		return getRequest().getSession().getServletContext().getRealPath(path);
	}
}
