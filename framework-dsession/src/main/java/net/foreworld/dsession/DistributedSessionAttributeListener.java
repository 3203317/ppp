package net.foreworld.dsession;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * DistributedSessionAttributeListener
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class DistributedSessionAttributeListener implements
		HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("attributeRemoved");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("attributeReplaced");
	}
}
