package net.foreworld.dsession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class HttpSession {

	public void setAttribute(String name, Object val) {
		javax.servlet.http.HttpSession session1 = getSession();
		session1.setAttribute(name, val);
	}

	public void invalidate() {
		javax.servlet.http.HttpSession session1 = getSession();
		session1.invalidate();
	}

	public static javax.servlet.http.HttpSession getSession() {
		javax.servlet.http.HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}
