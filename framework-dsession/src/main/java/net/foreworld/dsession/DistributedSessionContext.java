package net.foreworld.dsession;

import net.foreworld.dsession.utils.SessionProp;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public interface DistributedSessionContext {

	public final static int COOKIE_MAXAGE = Integer.valueOf(SessionProp
			.get("cookie.maxAge"));

	public static final String COOKIE_NAME_APIKEY = "apiKey";
	public static final String COOKIE_NAME_CURTIME = "curTime";

	public static final String BLANK = " ";
}
