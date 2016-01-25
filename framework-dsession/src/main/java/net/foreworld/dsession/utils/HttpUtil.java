package net.foreworld.dsession.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class HttpUtil extends net.foreworld.util.HttpUtil {
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}