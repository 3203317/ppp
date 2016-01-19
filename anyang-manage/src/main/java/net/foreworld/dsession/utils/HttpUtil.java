package net.foreworld.dsession.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class HttpUtil {
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}

	/**
	 * 新增Cookie
	 *
	 * @param res
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie存放时间（单位为秒；3天：3*24*60*60；值为0，则cookie随浏览器关闭而清除）
	 */
	public static void addCookie(HttpServletResponse res, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		res.addCookie(cookie);
	}

	/**
	 * 获取Cookie的值
	 *
	 * @param req
	 * @param name
	 *            cookie名称
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest req, String name) {
		Map<String, Cookie> cookieMap = HttpUtil.readCookieMap(req);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} // END
		return null;
	}

	protected static Map<String, Cookie> readCookieMap(
			HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			} // End
		} // END
		return cookieMap;
	}

	/**
	 * 获取应用动态域名
	 *
	 * @param req
	 * @return
	 */
	public static String getDynamicName(HttpServletRequest req) {
		String dyName = "http://" + req.getLocalName() + ":"
				+ req.getLocalPort() + req.getContextPath();
		return dyName;
	}
}
