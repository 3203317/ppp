package net.foreworld.dsession.impl;

import javax.servlet.http.HttpServletRequest;

import net.foreworld.dsession.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class HttpSessionImpl implements HttpSession {

	public void setAttribute(String name, Object val) {
		javax.servlet.http.HttpSession session1 = getSession();
		session1.setAttribute(name, val);
	}

	public void invalidate() {
		javax.servlet.http.HttpSession session1 = getSession();
		session1.invalidate();
	}

	public Object getAttribute(String name) {
		javax.servlet.http.HttpSession session1 = getSession();
		return session1.getAttribute(name);
	}

	public static javax.servlet.http.HttpSession getSession() {
		javax.servlet.http.HttpSession session = null;
		session = getRequest().getSession();
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}
