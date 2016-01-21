package net.foreworld.dsession.impl;

import java.util.logging.Logger;

import net.foreworld.dsession.DistributedSessionContext;
import net.foreworld.dsession.HttpSession;
import net.foreworld.dsession.utils.HttpUtil;
import net.foreworld.dsession.utils.RedisUtil;
import net.foreworld.dsession.utils.SerializeUtil;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionImpl implements HttpSession {
	private final Logger logger;

	public DistributedSessionImpl() {
		logger = Logger.getLogger(getClass().getName());
	}

	public void setAttribute(String name, Object value) {
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		RedisUtil.getJedis().set((apiKey + ":" + name).getBytes(),
				SerializeUtil.serialize(value));
	}

	public void invalidate() {
		javax.servlet.http.HttpSession session = HttpUtil.getSession();
		session.invalidate();
	}

	public Object getAttribute(String name) {
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		Object obj = SerializeUtil.unserialize(RedisUtil.getJedis().get(
				(apiKey + ":" + name).getBytes()));
		return obj;
	}
}
