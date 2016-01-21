package net.foreworld.dsession.utils;

import net.foreworld.dsession.DistributedSessionContext;

import org.apache.log4j.Logger;

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
		logger = Logger.getLogger(RedisUtil.class);
	}

	private RedisUtil() {
	}

	private static synchronized void initPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(Integer.valueOf(SysCfgUtil.get("db.maxIdle")));
			config.setMaxWaitMillis(Integer.valueOf(SysCfgUtil
					.get("db.maxWaitMillis")));
			config.setTestOnBorrow(Boolean.valueOf(SysCfgUtil
					.get("db.testOnBorrow")));
			// TIMEOUT 最大延迟时间
			jedisPool = new JedisPool(config,
					DistributedSessionContext.DB_HOST,
					Integer.valueOf(SysCfgUtil.get("db.port")),
					Integer.valueOf(SysCfgUtil.get("db.timeout")),
					SysCfgUtil.get("db.pass"));
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
		if (null == jedisPool)
			initPool();
		// TODO
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			if (null != jedis)
				jedis.close();
		}
	}
}
