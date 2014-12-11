package com.xcysoft.framework.core.logs;

import org.apache.log4j.Logger;

/**
 * 日志接口
 *
 * @author huangxin
 */
public interface LogEnabled {
	public Logger log = Logger.getRootLogger();
}
