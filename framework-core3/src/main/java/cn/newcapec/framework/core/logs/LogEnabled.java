package cn.newcapec.framework.core.logs;

import org.apache.log4j.Logger;

public abstract interface LogEnabled {
	public static final Logger log = Logger.getRootLogger();
}