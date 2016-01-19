package net.foreworld.dsession;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
		logger.info(cfg.getInitParameter("abc"));
		HttpServletRequest hreq = (HttpServletRequest) req;
		logger.info(hreq.getRequestURI());

		logger.info(hreq.getRequestURL().toString());
		// TODO
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		this.cfg = cfg;
	}
}
