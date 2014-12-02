package net.foreworld.framework.core.logs;

import org.apache.log4j.Logger;

/**
 * 日志接口
 *
 * @author huangxin
 */
public interface LogEnabled {
	/**
	 * 日志打印
	 */
	public Logger log = Logger.getRootLogger();

}
