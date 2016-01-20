package net.foreworld.dsession;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.foreworld.dsession.utils.HttpUtil;
import net.foreworld.dsession.utils.RestUtil;
import net.foreworld.dsession.utils.StringUtil;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class SessionFilter implements Filter {
	private final Logger logger;
	private FilterConfig cfg;

	public SessionFilter() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void destroy() {
		logger.warning("destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hres = (HttpServletResponse) res;

		// TODO
		Cookie[] cookies = hreq.getCookies();
		// 判断是否支持cookie
		if (null == cookies) {
			chain.doFilter(new DistributedSessionRequest(hreq), res);
			return;
		} // END

		String realIP = HttpUtil.getClientRealIP(hreq);
		// 判断IP
		if (null == StringUtil.isEmpty(realIP)) {
			chain.doFilter(new DistributedSessionRequest(hreq), res);
			return;
		} // END

		// 获取apikey
		String apiKey = HttpUtil.getCookie(hreq,
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		String curTime = HttpUtil.getCookie(hreq,
				DistributedSessionContext.COOKIE_NAME_CURTIME);
		String signature = HttpUtil.getCookie(hreq, apiKey);

		if (checkCookiesExists(apiKey, curTime, signature)) {
			apiKey = hreq.getSession().getId();
			HttpUtil.addCookie(hres,
					DistributedSessionContext.COOKIE_NAME_APIKEY, apiKey,
					DistributedSessionContext.COOKIE_MAXAGE);

			// 当前时间
			curTime = String.valueOf((new Date()).getTime());
			HttpUtil.addCookie(hres,
					DistributedSessionContext.COOKIE_NAME_CURTIME, curTime,
					DistributedSessionContext.COOKIE_MAXAGE);

			// 真实IP
			signature = RestUtil.standard(apiKey, apiKey + " " + curTime + " "
					+ realIP);

			// TODO
			HttpUtil.addCookie(hres, apiKey, signature,
					DistributedSessionContext.COOKIE_MAXAGE);
		} // END

		// TODO
		chain.doFilter(new DistributedSessionRequest(hreq), res);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		this.cfg = cfg;
	}

	/**
	 * 判断cookies所需参数是否为空
	 *
	 * @param apiKey
	 * @param curTime
	 * @param signature
	 * @return
	 */
	private boolean checkCookiesExists(String apiKey, String curTime,
			String signature) {
		return null == StringUtil.isEmpty(apiKey)
				|| null == StringUtil.isEmpty(curTime)
				|| null == StringUtil.isEmpty(signature);
	}
}
