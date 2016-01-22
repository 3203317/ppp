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

		// TODO
		String apiKey = getApiKey();
		if (null == apiKey)
			return;

		// TODO
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return;

			// TODO
			jedis.hset(apiKey.getBytes(), name.getBytes(),
					SerializeUtil.serialize(value));
			jedis.expire(apiKey, DistributedSessionContext.COOKIE_MAXAGE);
		} catch (Exception ignore) {
		} finally {
			if (null != jedis) {
				try {
					jedis.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	public void invalidate() {
		String apiKey = getApiKey();
		if (null == apiKey)
			return;

		// TODO
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return;

			// 删除多个keys
			// Set<String> values = jedis.keys(apiKey + ":*");
			// for (String value : values) {
			// jedis.del(value);
			// }

			jedis.del(apiKey);
		} catch (Exception ignore) {
		} finally {
			if (null != jedis) {
				try {
					jedis.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	public Object getAttribute(String name) {
		name = StringUtil.isEmpty(name);
		if (null == name)
			return null;

		// TODO
		String apiKey = getApiKey();
		if (null == apiKey)
			return null;

		// TODO
		byte[] b = null;
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return null;

			b = jedis.hget(apiKey.getBytes(), name.getBytes());

			if (null == b)
				return null;
		} catch (Exception ignore) {
		} finally {
			if (null != jedis) {
				try {
					jedis.close();
				} catch (Exception ignore) {
				}
			}
		} // END

		Object obj = SerializeUtil.unserialize(b);
		return obj;
	}

	private String getApiKey() {
		String apiKey = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);
		return apiKey;
	}
}
