package com.qingshu.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 上下文工具类
 * 
 */
public class ContextUtils {
	/**
	 * SpringMvc下获取request
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * SpringMvc下获取response
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response=((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}

	/**
	 * SpringMvc下获取session
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}
	/**
	 * SpringMvc下获取ApplicationContext
	 */
	public static ApplicationContext getApplicationContext()
	{
		ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
		return context;
	}
	/**
	 * SpringMvc下获取ApplicationContext
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName)
	{
		return (T) getApplicationContext().getBean(beanName);
	}
}
