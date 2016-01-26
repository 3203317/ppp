package net.foreworld.dsession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * DistributedSessionRequest
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
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