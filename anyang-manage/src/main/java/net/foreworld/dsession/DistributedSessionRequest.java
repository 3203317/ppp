package net.foreworld.dsession;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionRequest extends HttpServletRequestWrapper {
	private final Logger logger;

	public DistributedSessionRequest(HttpServletRequest request) {
		super(request);
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public HttpSession getSession() {
		logger.info(getRequestURI());
		logger.info(getRequestURL().toString());
		return super.getSession();
	}
}