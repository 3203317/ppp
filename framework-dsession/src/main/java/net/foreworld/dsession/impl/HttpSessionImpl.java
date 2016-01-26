package net.foreworld.dsession.impl;

import net.foreworld.dsession.HttpSession;
import net.foreworld.dsession.utils.HttpUtil;

/**
 * HttpSessionImpl
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class HttpSessionImpl implements HttpSession {

	public void setAttribute(String name, Object value) {
		javax.servlet.http.HttpSession session = HttpUtil.getSession();
		session.setAttribute(name, value);
	}

	public void invalidate() {
		javax.servlet.http.HttpSession session = HttpUtil.getSession();
		session.invalidate();
	}

	public Object getAttribute(String name) {
		javax.servlet.http.HttpSession session = HttpUtil.getSession();
		return session.getAttribute(name);
	}
}
