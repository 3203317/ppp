package net.foreworld.dsession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class DistributedSessionRequest extends HttpServletRequestWrapper {

	public DistributedSessionRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public HttpSession getSession() {
		return super.getSession();
	}
}