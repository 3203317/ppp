package net.foreworld.dsession.impl;

import net.foreworld.dsession.DistributedSessionContext;
import net.foreworld.dsession.HttpSession;
import net.foreworld.dsession.utils.HttpUtil;
import net.foreworld.util.RestUtil;
import net.foreworld.util.SerializeUtil;
import net.foreworld.util.StringUtil;
import net.foreworld.util.redis.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * DistributedSessionImpl
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class DistributedSessionImpl implements HttpSession {

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

		if (!checkSignSafe(apiKey))
			return;

		// TODO
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return;

			// TODO
			if (jedis.exists(apiKey)) {
				jedis.hset(apiKey.getBytes(), name.getBytes(),
						SerializeUtil.serialize(value));
			} else {
				jedis.hset(apiKey.getBytes(), name.getBytes(),
						SerializeUtil.serialize(value));
				jedis.expire(apiKey, DistributedSessionContext.COOKIE_MAXAGE);
			} // END
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

		if (!checkSignSafe(apiKey))
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

		if (!checkSignSafe(apiKey))
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

	private String getCurTime() {
		String curTime = HttpUtil.getCookie(HttpUtil.getRequest(),
				DistributedSessionContext.COOKIE_NAME_CURTIME);
		curTime = StringUtil.isEmpty(curTime);
		return curTime;
	}

	private String getSignature(String apiKey) {
		String signature = HttpUtil.getCookie(HttpUtil.getRequest(), apiKey);
		signature = StringUtil.isEmpty(signature);
		return signature;
	}

	/**
	 * 检查签名安全
	 *
	 * @return
	 */
	private boolean checkSignSafe(String apiKey) {
		String curTime = getCurTime();
		// 检查curTime
		if (null == curTime)
			return false;

		String signature = getSignature(apiKey);
		// 检查signature
		if (null == signature)
			return false;

		String realIP = HttpUtil.getClientRealIP(HttpUtil.getRequest());
		realIP = StringUtil.isEmpty(realIP);
		// 检查IP
		if (null == realIP)
			return false;

		return signature.equals(RestUtil.genSignature(apiKey, apiKey
				+ DistributedSessionContext.BLANK + curTime
				+ DistributedSessionContext.BLANK + realIP));
	}
}
