package net.foreworld.dsession.impl;

import net.foreworld.dsession.HttpSession;
import net.foreworld.dsession.utils.HttpUtil;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionImpl implements HttpSession {

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
