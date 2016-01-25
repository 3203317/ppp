package net.foreworld.util.redis;

import java.util.logging.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public final class RedisUtil {
	private static final Logger logger;

	private static JedisPool jedisPool = null;

	static {
		logger = Logger.getLogger(RedisUtil.class.getName());
	}

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
			logger.info(e.getMessage());
		}
	}

	/**
	 * 获取jedis实例
	 *
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		if (null == jedisPool)
			initPool();
		// TODO
		Jedis jedis = null;
		try {
			if (null == jedisPool)
				return null;
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
}
