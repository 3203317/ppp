package net.foreworld.dsession.impl;

import java.util.logging.Logger;

import net.foreworld.dsession.DistributedSessionContext;
import net.foreworld.dsession.HttpSession;
import net.foreworld.dsession.utils.HttpUtil;
import net.foreworld.dsession.utils.RedisUtil;
import net.foreworld.dsession.utils.SerializeUtil;
import net.foreworld.dsession.utils.StringUtil;
import redis.clients.jedis.Jedis;

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
		if (null == value)
			return;

		name = StringUtil.isEmpty(name);
		if (null == name)
			return;

		Jedis jedis = RedisUtil.getJedis();
		if (null == jedis)
			return;

		// TODO
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		// TODO
		if (null != apiKey)
			jedis.set((apiKey + ":" + name).getBytes(),
					SerializeUtil.serialize(value));
	}

	public void invalidate() {
		Jedis jedis = RedisUtil.getJedis();
		if (null == jedis)
			return;

		// TODO
		javax.servlet.http.HttpSession session = HttpUtil.getSession();
		session.invalidate();
	}

	public Object getAttribute(String name) {
		name = StringUtil.isEmpty(name);
		if (null == name)
			return null;

		Jedis jedis = RedisUtil.getJedis();
		if (null == jedis)
			return null;

		// TODO
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		if (null == apiKey)
			return null;

		byte[] b = jedis.get((apiKey + ":" + name).getBytes());

		if (null == b)
			return null;

		Object obj = SerializeUtil.unserialize(b);

		return obj;
	}
}
