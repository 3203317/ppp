package net.foreworld.dsession;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.foreworld.dsession.utils.HttpUtil;
import net.foreworld.util.RestUtil;
import net.foreworld.util.StringUtil;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class SessionFilter implements Filter {
	private final Logger logger;
	private String urlSuffix;

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

		// TODO
		if (!checkUrlSafe(hreq)) {
			chain.doFilter(req, res);
			return;
		}

		String realIP = HttpUtil.getClientRealIP(hreq);
		realIP = StringUtil.isEmpty(realIP);
		// 判断IP
		if (null == realIP) {
			chain.doFilter(req, res);
			return;
		} // END

		// 获取apikey
		String apiKey = HttpUtil.getCookie(hreq,
				DistributedSessionContext.COOKIE_NAME_APIKEY);
		apiKey = StringUtil.isEmpty(apiKey);

		// TODO
		String curTime = HttpUtil.getCookie(hreq,
				DistributedSessionContext.COOKIE_NAME_CURTIME);
		curTime = StringUtil.isEmpty(curTime);

		// TODO
		String signature = HttpUtil.getCookie(hreq, apiKey);
		signature = StringUtil.isEmpty(signature);

		// TODO
		HttpServletResponse hres = (HttpServletResponse) res;

		if (checkCookiesExists(apiKey, curTime, signature)) {
			// 当前时间
			curTime = String.valueOf((new Date()).getTime());
			HttpUtil.addCookie(hres,
					DistributedSessionContext.COOKIE_NAME_CURTIME, curTime,
					DistributedSessionContext.COOKIE_MAXAGE);

			// TODO
			apiKey = RestUtil.genSignature(UUID.randomUUID().toString(),
					curTime);
			HttpUtil.addCookie(hres,
					DistributedSessionContext.COOKIE_NAME_APIKEY, apiKey,
					DistributedSessionContext.COOKIE_MAXAGE);

			// 真实IP
			signature = RestUtil.genSignature(apiKey, apiKey
					+ DistributedSessionContext.BLANK + curTime
					+ DistributedSessionContext.BLANK + realIP);

			// TODO
			HttpUtil.addCookie(hres, apiKey, signature,
					DistributedSessionContext.COOKIE_MAXAGE);
		} // END

		logger.info("signature: " + HttpUtil.getCookie(hreq, apiKey));

		// TODO
		chain.doFilter(new DistributedSessionRequest(hreq), res);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		urlSuffix = "," + cfg.getInitParameter("url-suffix") + ",";
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
		return null == apiKey || null == curTime || null == signature;
	}

	/**
	 * 检测Url是否安全
	 *
	 * @param url
	 * @return 安全返回true
	 */
	private boolean checkUrlSafe(HttpServletRequest req) {
		String suffix = HttpUtil.getUrlSuffix(req);
		// TODO
		if (null == suffix)
			return true;
		// TODO
		return urlSuffix.indexOf("," + suffix.toLowerCase() + ",") == -1;
	}
}
