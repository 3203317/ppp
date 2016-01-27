package net.foreworld.util.redis;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisUtil
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public final class RedisUtil {
	private static final Logger logger = Logger.getLogger(RedisUtil.class);

	private static JedisPool jedisPool = null;

	private RedisUtil() {
	}

	private static synchronized void initPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(Integer.valueOf(RedisProp.get("db.maxIdle")));
			config.setMaxWaitMillis(Integer.valueOf(RedisProp
					.get("db.maxWaitMillis")));
			config.setTestOnBorrow(Boolean.valueOf(RedisProp
					.get("db.testOnBorrow")));
			// TIMEOUT 最大延迟时间
			jedisPool = new JedisPool(config, RedisProp.get("db.host"),
					Integer.valueOf(RedisProp.get("db.port")),
					Integer.valueOf(RedisProp.get("db.timeout")),
					RedisProp.get("db.pass"));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 获取jedis实例
	 *
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		if (null == jedisPool) {
			initPool();
		} // END
		try {
			return null == jedisPool ? null : jedisPool.getResource();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}
