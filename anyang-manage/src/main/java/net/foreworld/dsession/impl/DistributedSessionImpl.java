package net.foreworld.dsession.impl;

import java.util.Set;
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

		// TODO
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		// TODO
		if (null == apiKey)
			return;

		// TODO
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return;

			// TODO
			jedis.setex((apiKey + ":" + name).getBytes(),
					DistributedSessionContext.COOKIE_MAXAGE,
					SerializeUtil.serialize(value));
		} catch (Exception ignore) {
		} finally {
			if (null != jedis)
				jedis.close();
		}
	}

	public void invalidate() {
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		if (null == apiKey)
			return;

		// TODO
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return;

			Set<String> values = jedis.keys(apiKey + ":*");

			for (String value : values) {
				jedis.del(value);
			}
		} catch (Exception ignore) {
		} finally {
			if (null != jedis)
				jedis.close();
		}
	}

	public Object getAttribute(String name) {
		name = StringUtil.isEmpty(name);
		if (null == name)
			return null;

		// TODO
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		if (null == apiKey)
			return null;

		Jedis jedis = RedisUtil.getJedis();
		if (null == jedis)
			return null;

		byte[] b = jedis.get((apiKey + ":" + name).getBytes());
		jedis.close();

		if (null == b)
			return null;

		Object obj = SerializeUtil.unserialize(b);

		return obj;
	}
}
