package net.foreworld.dsession;

import net.foreworld.dsession.utils.SessionProp;
import net.foreworld.util.redis.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionContext {

	public final static int COOKIE_MAXAGE = Integer.valueOf(SessionProp
			.get("cookie.maxAge"));

	public static final String COOKIE_NAME_APIKEY = "apiKey";
	public static final String COOKIE_NAME_CURTIME = "curTime";

	public static final String BLANK = " ";

	/**
	 * 获取在线人数
	 *
	 * @return
	 */
	public static long getOnlineNum() {
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			if (null == jedis)
				return 0;
			return jedis.dbSize();
		} catch (Exception ignore) {
		} finally {
			if (null != jedis) {
				try {
					jedis.close();
				} catch (Exception ignore) {
				}
			}
		}
		return 0;
	}
}
