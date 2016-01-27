package net.foreworld.dsession;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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

import org.apache.log4j.Logger;

/**
 * SessionFilter
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class SessionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(SessionFilter.class);
	private static final String URL_SUFFIX = "url-suffix";
	private static final String COMMA = ",";

	private String urlSuffix;

	@Override
	public void destroy() {
		logger.info("filter destroy");
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

		// 判断IP
		String realIP = HttpUtil.getClientRealIP(hreq);
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
		urlSuffix = COMMA + cfg.getInitParameter(URL_SUFFIX) + COMMA;
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
		return null == suffix ? true : urlSuffix.indexOf(COMMA
				+ suffix.toLowerCase() + COMMA) == -1;
	}
}
